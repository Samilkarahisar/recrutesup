package com.polytech.recrutesup.mappers;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.polytech.recrutesup.dto.WishDTO;
import com.polytech.recrutesup.entities.CompanyWish;
import com.polytech.recrutesup.entities.StudentWish;

@Mapper(componentModel = "spring")
@Named("WishMapper")
public interface WishMapper {

	@Named("companyWishSended")
	@Mappings({
        @Mapping(target = "sender", source = "company.name"),
	    @Mapping(target = "receiver", expression = "java(companyWish.getStudent().getUser().getFirstname() + \" \" + companyWish.getStudent().getUser().getLastname())"),
	    @Mapping(target = "priority", expression = "java(companyWish.getPrioritySender())")})
	WishDTO companyWishSendedToWishDTO(CompanyWish companyWish);
	
	@Named("companyWishReceived")
	@Mappings({
        @Mapping(target = "sender", source = "company.name"),
	    @Mapping(target = "receiver", expression = "java(companyWish.getStudent().getUser().getFirstname() + \" \" + companyWish.getStudent().getUser().getLastname())"),
	    @Mapping(target = "priority", expression = "java(companyWish.getPriorityReceiver())")})
	WishDTO companyWishReceivedToWishDTO(CompanyWish companyWish);
	
	@Named("studentWishSended")
	@Mappings({
        @Mapping(target = "sender", expression = "java(studentWish.getStudent().getUser().getFirstname() + \" \" + studentWish.getStudent().getUser().getLastname())"),
	    @Mapping(target = "receiver", expression = "java(studentWish.getOffer().getCompany().getName() + \" : \" + studentWish.getOffer().getLabel())"),
	    @Mapping(target = "priority", expression = "java(studentWish.getPrioritySender())")})
	WishDTO studentWishSendedToWishDTO(StudentWish studentWish);
	
	@Named("studentWishReceived")
	@Mappings({
        @Mapping(target = "sender", expression = "java(studentWish.getStudent().getUser().getFirstname() + \" \" + studentWish.getStudent().getUser().getLastname())"),
	    @Mapping(target = "receiver", expression = "java(studentWish.getOffer().getCompany().getName() + \" : \" + studentWish.getOffer().getLabel())"),
	    @Mapping(target = "priority", expression = "java(studentWish.getPriorityReceiver())")})
	WishDTO studentWishReceivedToWishDTO(StudentWish studentWish);
	
	@IterableMapping(qualifiedByName = "companyWishSended")
	@Named("listCompanyWishSended")
	List<WishDTO> listCompanyWishSendedToListWishDTO(List<CompanyWish> listComapnyWish);
	
	@IterableMapping(qualifiedByName = "companyWishReceived")
	@Named("listCompanyWishReceived")
	List<WishDTO> listCompanyWishReceivedToListWishDTO(List<CompanyWish> listComapnyWish);
	
	@IterableMapping(qualifiedByName = "studentWishSended")
	@Named("listStudentWishSended")
	List<WishDTO> listStudentWishSendedToListWishDTO(List<StudentWish> listStudentWish);
	
	@IterableMapping(qualifiedByName = "studentWishReceived")
	@Named("listStudentWishReceived")
	List<WishDTO> listStudentWishReceivedToListWishDTO(List<StudentWish> listStudentWish);
}
