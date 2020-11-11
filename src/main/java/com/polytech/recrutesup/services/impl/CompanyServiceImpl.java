package com.polytech.recrutesup.services.impl;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.recrutesup.dto.CompanyDTO;
import com.polytech.recrutesup.dto.CreateCompanyDTO;
import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.reference.EWorkflowState;
import com.polytech.recrutesup.exceptions.RecruteSupApplicationException;
import com.polytech.recrutesup.exceptions.RecruteSupErrorType;
import com.polytech.recrutesup.mappers.CompanyMapper;
import com.polytech.recrutesup.repositories.CompanyRepository;
import com.polytech.recrutesup.services.CompanyService;
import com.polytech.recrutesup.services.dto.CompanyServiceDTO;

@Service
public class CompanyServiceImpl implements CompanyService, CompanyServiceDTO {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Override
	public Company findOne(Long id) {
		Optional<Company> company = this.companyRepository.findById(id);
		if(!company.isPresent()) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.COMPANY_UNKNOWN);
		}
		return company.get();
	}
	
	@Override
	public CompanyDTO createCompany(@NotNull @Valid CreateCompanyDTO createCompanyDTO) {

		Company company = companyRepository.findOneByName(createCompanyDTO.getName());
		if(company != null) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.COMPANT_ALREADY_CREATED);
		}
		
		company = this.companyMapper.CreateCompanyDTOToCompany(createCompanyDTO);
		company.setState(EWorkflowState.ENREGISTRE);
		company.setEmployees(new ArrayList<>());
		
		company = this.companyRepository.save(company);
		
		return this.companyMapper.CompanyToCompanyDTO(company);		
	}

}
