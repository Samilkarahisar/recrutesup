package com.polytech.recrutesup.mappers;

import com.polytech.recrutesup.dto.OfferDTO;
import com.polytech.recrutesup.dto.OfferLightDTO;
import com.polytech.recrutesup.entities.Attachment;
import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.Offer;
import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.payload.request.CreateOfferRequest;
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

    @Mappings({
            @Mapping(source = "offer.company.name", target = "companyName"),
    })
    OfferLightDTO offerToOfferLightDTO(Offer offer);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "state", ignore = true),
        @Mapping(target = "creationDate", ignore = true),
        @Mapping(source = "createOfferRequest.mailAddress", target = "mailAddress"),
        @Mapping(source = "createOfferRequest.description", target = "description"),
        @Mapping(source = "user", target = "createdByUser"),
        @Mapping(source = "company", target="company")
    })
    Offer createOfferRequestToOffer(CreateOfferRequest createOfferRequest, Company company,
                                    List<Attachment> attachmentList, User user);
}
