package com.polytech.recrutesup.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.polytech.recrutesup.dto.WishDTO;
import com.polytech.recrutesup.entities.CompanyWish;
import com.polytech.recrutesup.entities.StudentWish;

@Mapper(componentModel = "spring")
public interface WishMapper {

	@Mappings({
        @Mapping(target = "sender", source = "company.name"),
	    @Mapping(target = "receiver", expression = "java(companyWish.getStudent().getUser().getFirstname() + \" \" + companyWish.getStudent().getUser().getLastname())"),
	    @Mapping(target = "priority", ignore = true)})
	WishDTO companyWishToWishDTO(CompanyWish companyWish);
	
	@Mappings({
        @Mapping(target = "sender", expression = "java(studentWish.getStudent().getUser().getFirstname() + \" \" + studentWish.getStudent().getUser().getLastname())"),
	    @Mapping(target = "receiver", source = "offer.label"),
	    @Mapping(target = "priority", ignore = true)})
	WishDTO studentWishToWishDTO(StudentWish studentWish);
}
