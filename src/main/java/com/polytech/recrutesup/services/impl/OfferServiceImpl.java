package com.polytech.recrutesup.services.impl;

import com.polytech.recrutesup.dto.OfferDTO;
import com.polytech.recrutesup.dto.OfferLightDTO;
import com.polytech.recrutesup.entities.Attachment;
import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.Offer;
import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.entities.reference.EWorkflowState;
import com.polytech.recrutesup.exceptions.RecruteSupApplicationException;
import com.polytech.recrutesup.exceptions.RecruteSupErrorType;
import com.polytech.recrutesup.mappers.OfferMapper;
import com.polytech.recrutesup.payload.request.CreateOfferRequest;
import com.polytech.recrutesup.repositories.AttachmentRepository;
import com.polytech.recrutesup.repositories.CompanyRepository;
import com.polytech.recrutesup.repositories.OfferRepository;
import com.polytech.recrutesup.repositories.UserRepository;
import com.polytech.recrutesup.services.OfferService;
import com.polytech.recrutesup.services.dto.OfferServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService, OfferServiceDTO {

    @Autowired
    private OfferMapper offerMapper;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<OfferDTO> getAllOfferByCompanyId(@NotNull Long idCompany) {

        List<OfferDTO> offerDTOList = new ArrayList<>();

        Optional<Company> company = companyRepository.findById(idCompany);

        if (company.isPresent()) {
            List<Offer> allOffersByCompany = offerRepository.findAllByCompany(company.get());
            allOffersByCompany
                    .forEach(offer -> {
                        offerDTOList.add(offerMapper.offerToOfferDTO(offer));
                    });
        }

        return offerDTOList;
    }

    @Override
    public Optional<OfferDTO> getOffer(@NotNull Long idStudent) {


        return Optional.empty();
    }

    @Override
    public List<OfferDTO> getAllOffer() {

        List<OfferDTO> offerDTOList = new ArrayList<>();

        List<Offer> allOffersByCompany = offerRepository.findAll();
        allOffersByCompany
                .forEach(offer -> {
                    offerDTOList.add(offerMapper.offerToOfferDTO(offer));
                });

        return offerDTOList;
    }

    @Override
    public List<OfferLightDTO> getAllOfferLight() {

        List<OfferLightDTO> offerLightDTOList = new ArrayList<>();

        List<Offer> allOffers = offerRepository.findAll();
        allOffers
                .forEach(offer -> {
                    offerLightDTOList.add(offerMapper.offerToOfferLightDTO(offer));
                });

        return offerLightDTOList;
    }

    @Override
    public OfferDTO createOffer(@NotNull @Valid CreateOfferRequest createOfferRequest) {

        Optional<User> user = userRepository.findById(createOfferRequest.getUserId());
        Optional<Company> company = companyRepository.findByEmployeesContains(user.get());
        Offer offer = new Offer();

        if(company.isPresent()){
            offer = offerMapper.createOfferRequestToOffer(createOfferRequest, company.get());
            offer.setState(EWorkflowState.ENREGISTRE);

            offerRepository.save(offer);
        }

        return offerMapper.offerToOfferDTO(offer);
    }

    @Override
    public Offer findOne(Long id) {

        Optional<Offer> offer = this.offerRepository.findById(id);
        if (!offer.isPresent()) {
            throw new RecruteSupApplicationException(RecruteSupErrorType.OFFER_UNKNOWN);
        }
        return offer.get();
    }



}
