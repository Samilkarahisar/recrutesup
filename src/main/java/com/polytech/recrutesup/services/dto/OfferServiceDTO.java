package com.polytech.recrutesup.services.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.polytech.recrutesup.dto.OfferDTO;
import com.polytech.recrutesup.dto.OfferLightDTO;
import com.polytech.recrutesup.payload.request.CreateOfferRequest;

public interface OfferServiceDTO {

    List<OfferDTO> getAllOfferByCompanyId(@NotNull Long idCompany);

    OfferDTO getOffer(@NotNull Long idOffer);

    List<OfferDTO> getAllOffer();

    List<OfferLightDTO> getAllOfferLight();

    List<OfferLightDTO> getAllOfferByCompanyIdLight(@NotNull Long idCompany);

    OfferDTO createOffer(@NotNull @Valid CreateOfferRequest createOfferRequest);
    
    OfferDTO updateOffer(@NotNull Long idOffer, @NotNull @Valid CreateOfferRequest offerDTO);
    
    OfferDTO updateStateOffer(@NotNull Long idOffer, @NotNull String currentState, @NotNull String nextState);
}
