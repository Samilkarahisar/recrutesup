package com.polytech.recrutesup.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.polytech.recrutesup.repositories.CompanyRepository;
import com.polytech.recrutesup.repositories.OfferRepository;
import com.polytech.recrutesup.repositories.UserRepository;
import com.polytech.recrutesup.services.OfferService;
import com.polytech.recrutesup.services.dto.OfferServiceDTO;

@Service
public class OfferServiceImpl implements OfferService, OfferServiceDTO {

    @Autowired
    private OfferMapper offerMapper;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CompanyRepository companyRepository;

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
    public OfferDTO getOffer(@NotNull Long idOffer) {

        Optional<Offer> offer = offerRepository.findById(idOffer);

        if (offer.isPresent()) {
            return offerMapper.offerToOfferDTO(offer.get());
        } else {
            throw new RecruteSupApplicationException(RecruteSupErrorType.OFFER_UNKNOWN);
        }
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
    public List<OfferLightDTO> getAllOfferByCompanyIdLight(@NotNull Long idCompany) {

        List<OfferLightDTO> offerLightDTOList = new ArrayList<>();

        Optional<Company> company = companyRepository.findById(idCompany);

        if (company.isPresent()) {
            List<Offer> allOffersByCompany = offerRepository.findAllByCompany(company.get());
            allOffersByCompany
                    .forEach(offer -> {
                        offerLightDTOList.add(offerMapper.offerToOfferLightDTO(offer));
                    });
        }

        return offerLightDTOList;
    }

    @Override
    public OfferDTO createOffer(@NotNull @Valid CreateOfferRequest createOfferRequest) {

        List<Attachment> attachmentList = new ArrayList<>();
        Offer offer = new Offer();

        Optional<User> user = userRepository.findById(createOfferRequest.getUserId());
        Optional<Company> company = companyRepository.findByEmployeesContains(user.get());

        if (createOfferRequest.getAttachmentNamesList() != null && !createOfferRequest.getAttachmentNamesList().isEmpty()) {
            for (String name : createOfferRequest.getAttachmentNamesList()) {
                Attachment attachment = new Attachment();
                attachment.setLabel(name);
                attachmentList.add(attachment);
            }
        }

        if (company.isPresent()) {
            offer = offerMapper.createOfferRequestToOffer(createOfferRequest, company.get(), attachmentList, user.get());
            offer.setState(EWorkflowState.ENREGISTRE);
            offer.setCreationDate(new Date(System.currentTimeMillis()));
            offer.setWishReceivedList(new ArrayList<>());
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

	@Override
	public OfferDTO updateOffer(@NotNull Long idOffer, @NotNull @Valid CreateOfferRequest offerDTO) {
		Offer offer = this.findOne(idOffer);
		this.offerMapper.updateOfferFromCreateOfferRequest(offerDTO, offer);	
		offer = offerRepository.save(offer);
		
		return offerMapper.offerToOfferDTO(offer);
	}


}
