package com.polytech.recrutesup.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.polytech.recrutesup.dto.AdminDTO;
import com.polytech.recrutesup.entities.Admin;
import com.polytech.recrutesup.payload.request.UpdateAdminRequest;

@Mapper(componentModel = "spring")
public interface AdminMapper {

	@Mappings({
		@Mapping(source = "user.firstname", target = "firstname"),
	    @Mapping(source = "user.lastname", target = "lastname"),
	    @Mapping(source = "user.mailAddress", target = "mailAddress"),
		@Mapping(source = "user.phoneNumber", target = "phoneNumber") })
	AdminDTO adminToAdminDTO(Admin admin);

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "user.firstname", source = "firstname"),
		@Mapping(target = "user.lastname", source = "lastname"),
		@Mapping(target = "user.mailAddress", source = "mailAddress"),
		@Mapping(target = "user.phoneNumber", source = "phoneNumber") })
	void updateAdminFromUpdateAdminRequest(UpdateAdminRequest adminDTO, @MappingTarget Admin admin);
}
