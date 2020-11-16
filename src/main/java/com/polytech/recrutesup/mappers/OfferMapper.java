package com.polytech.recrutesup.mappers;

import com.polytech.recrutesup.dto.OfferDTO;
import com.polytech.recrutesup.dto.OfferLightDTO;
import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.Offer;
import com.polytech.recrutesup.payload.request.CreateOfferRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    @Mappings({
            @Mapping(source = "offer.createdByUser.firstname", target = "userFirstname"),
            @Mapping(source = "offer.createdByUser.lastname", target = "userLastname"),
    })
    OfferDTO offerToOfferDTO(Offer offer);

    @Mappings({
            @Mapping(source = "offer.company.name", target = "companyName"),
    })
    OfferLightDTO offerToOfferLightDTO(Offer offer);

    @Mappings({
        @Mapping(source = "createOfferRequest.mailAddress", target = "mailAddress"),
        @Mapping(source = "createOfferRequest.description", target = "description")
    })
    Offer createOfferRequestToOffer(CreateOfferRequest createOfferRequest, Company company);
}
