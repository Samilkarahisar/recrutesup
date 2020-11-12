package com.polytech.recrutesup.services.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.polytech.recrutesup.dto.CompanyDTO;
import com.polytech.recrutesup.dto.CreateCompanyDTO;
import com.polytech.recrutesup.dto.CreateEmployeeDTO;

public interface CompanyServiceDTO {

	CompanyDTO createCompany(@NotNull @Valid CreateCompanyDTO createCompanyDTO);
	
	CompanyDTO createEmployee(@NotNull @Valid CreateEmployeeDTO createEmployeeDTO);
	
	List<CompanyDTO> getAllCompanies();
}
