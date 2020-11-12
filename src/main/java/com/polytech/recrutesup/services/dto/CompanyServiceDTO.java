package com.polytech.recrutesup.services.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.polytech.recrutesup.dto.CompanyDTO;
import com.polytech.recrutesup.dto.CreateCompanyDTO;

public interface CompanyServiceDTO {

	CompanyDTO createCompany(@NotNull @Valid CreateCompanyDTO createCompanyDTO);
}
