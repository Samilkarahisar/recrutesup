package com.polytech.recrutesup.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.polytech.recrutesup.dto.CompanyDTO;
import com.polytech.recrutesup.dto.CreateCompanyDTO;
import com.polytech.recrutesup.entities.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "description", ignore = true),
		@Mapping(target = "state", ignore = true),
		@Mapping(target = "employees", ignore = true)
	})
	Company CreateCompanyDTOToCompany(CreateCompanyDTO createCompanyDTO);
	
	CompanyDTO CompanyToCompanyDTO(Company company);
}
