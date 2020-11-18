package com.polytech.recrutesup.services.dto;

import com.polytech.recrutesup.dto.OfferDTO;
import com.polytech.recrutesup.dto.OfferLightDTO;
import com.polytech.recrutesup.entities.Offer;
import com.polytech.recrutesup.payload.request.CreateOfferRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface OfferServiceDTO {

    List<OfferDTO> getAllOfferByCompanyId(@NotNull Long idCompany);

    OfferDTO getOffer(@NotNull Long idOffer);

    List<OfferDTO> getAllOffer();

    List<OfferLightDTO> getAllOfferLight();

    List<OfferLightDTO> getAllOfferByCompanyIdLight(@NotNull Long idCompany);

    OfferDTO createOffer(@NotNull @Valid CreateOfferRequest createOfferRequest);
}
