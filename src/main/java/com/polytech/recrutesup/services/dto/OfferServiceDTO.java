package com.polytech.recrutesup.services.dto;

import com.polytech.recrutesup.dto.OfferDTO;
import com.polytech.recrutesup.entities.Offer;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface OfferServiceDTO {

    List<OfferDTO> getAllOfferByCompanyId(@NotNull Long idCompany);

    Optional<OfferDTO> getOffer(@NotNull Long idStudent);

    List<OfferDTO> getAllOffer();
}
