package com.polytech.recrutesup.mappers;

import com.polytech.recrutesup.dto.OfferDTO;
import com.polytech.recrutesup.entities.Attachment;
import com.polytech.recrutesup.entities.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    @Mappings({
            @Mapping(source = "offer.createdByUser.firstname", target = "userFirstname"),
            @Mapping(source = "offer.createdByUser.lastname", target = "userLastname"),
    })
    OfferDTO offerToOfferDTO(Offer offer);

}
