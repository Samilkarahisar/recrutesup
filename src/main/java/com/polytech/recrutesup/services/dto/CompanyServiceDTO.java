package com.polytech.recrutesup.services.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.polytech.recrutesup.dto.CompanyDTO;
import com.polytech.recrutesup.dto.EmployeeDTO;
import com.polytech.recrutesup.dto.EmployeeLightDTO;
import com.polytech.recrutesup.payload.request.CreateCompanyRequest;
import com.polytech.recrutesup.payload.request.CreateEmployeeRequest;

public interface CompanyServiceDTO {

	// COMPANY
	CompanyDTO createCompany(@NotNull @Valid CreateCompanyRequest createCompanyDTO);

	List<CompanyDTO> getAllCompanies();

	CompanyDTO getCompany(@NotNull Long idCompany);

	CompanyDTO updateCompany(@NotNull Long idCompany, @NotNull @Valid CreateCompanyRequest companyDTO);

	// EMPLOYEE
	List<EmployeeDTO> getAllEmployees();

	List<EmployeeLightDTO> getAllEmployeesByCompany(@NotNull Long idCompany);

	EmployeeDTO getEmployee(@NotNull Long idUser);

	EmployeeDTO updateEmployee(@NotNull Long idUser, @NotNull @Valid CreateEmployeeRequest employeeDTO);

	EmployeeDTO createEmployee(@NotNull @Valid CreateEmployeeRequest createEmployeeDTO);
}
