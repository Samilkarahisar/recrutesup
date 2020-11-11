package com.polytech.recrutesup.services.impl;

import com.polytech.recrutesup.dto.OfferDTO;
import com.polytech.recrutesup.entities.Attachment;
import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.Offer;
import com.polytech.recrutesup.exceptions.RecruteSupApplicationException;
import com.polytech.recrutesup.exceptions.RecruteSupErrorType;
import com.polytech.recrutesup.mappers.OfferMapper;
import com.polytech.recrutesup.repositories.AttachmentRepository;
import com.polytech.recrutesup.repositories.CompanyRepository;
import com.polytech.recrutesup.repositories.OfferRepository;
import com.polytech.recrutesup.services.OfferService;
import com.polytech.recrutesup.services.dto.OfferServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
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


    @Override
    public List<OfferDTO> getAllOfferByCompanyId(@NotNull Long idCompany) {

        List<OfferDTO> offerDTOList = new ArrayList<>();

        Optional<Company> company = companyRepository.findById(idCompany);

        if (company.isPresent()) {
            List<Offer> allOffersByCompany = offerRepository.findAllByCompany(company.get());
            allOffersByCompany
                    .forEach(offer -> {
                        List<Attachment> attachmentList = attachmentRepository.findAllAttachmentByOffer(offer.getId());
                        offerDTOList.add(offerMapper.offerToOfferDTO(offer, attachmentList));
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
                    List<Attachment> attachmentList = attachmentRepository.findAllAttachmentByOffer(offer.getId());
                    offerDTOList.add(offerMapper.offerToOfferDTO(offer, attachmentList));
                });

        return offerDTOList;
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
