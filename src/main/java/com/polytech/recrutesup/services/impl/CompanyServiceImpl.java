package com.polytech.recrutesup.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.polytech.recrutesup.dto.CompanyDTO;
import com.polytech.recrutesup.dto.CreateCompanyDTO;
import com.polytech.recrutesup.dto.CreateEmployeeDTO;
import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.Role;
import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.entities.reference.ERole;
import com.polytech.recrutesup.entities.reference.EWorkflowState;
import com.polytech.recrutesup.exceptions.RecruteSupApplicationException;
import com.polytech.recrutesup.exceptions.RecruteSupErrorType;
import com.polytech.recrutesup.mappers.CompanyMapper;
import com.polytech.recrutesup.repositories.CompanyRepository;
import com.polytech.recrutesup.repositories.RoleRepository;
import com.polytech.recrutesup.services.CompanyService;
import com.polytech.recrutesup.services.dto.CompanyServiceDTO;

@Service
public class CompanyServiceImpl implements CompanyService, CompanyServiceDTO {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
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

		Optional<Company> optCompany = companyRepository.findByName(createCompanyDTO.getName());
		if(optCompany.isPresent()) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.COMPANT_ALREADY_CREATED);
		}
		
		Company company = this.companyMapper.CreateCompanyDTOToCompany(createCompanyDTO);
		company.setState(EWorkflowState.ENREGISTRE);
		company.setEmployees(new ArrayList<>());
		
		company = this.companyRepository.save(company);
		
		return this.companyMapper.CompanyToCompanyDTO(company);		
	}
	
	@Override
	public List<CompanyDTO> getAllCompanies() {
		
		List<CompanyDTO> listCompanyDTO = new ArrayList<>();
		
		List<Company> listCompany = this.companyRepository.findAll();
		listCompany.forEach(company -> listCompanyDTO.add(companyMapper.CompanyToCompanyDTO(company)));
	
		return listCompanyDTO;
	}
	

	@Override
	public CompanyDTO createEmployee(@NotNull @Valid CreateEmployeeDTO createEmployeeDTO) {
		
		Company company = this.findOne(createEmployeeDTO.getIdCompany());
		
		Role role = roleRepository.findByName(ERole.ROLE_COMPANY).orElseThrow(()-> new RecruteSupApplicationException(RecruteSupErrorType.ROLE_STUDENT_UNKNOWN));
		User employee = new User();
		employee.setFirstname(createEmployeeDTO.getFirstname().trim());
		employee.setLastname(createEmployeeDTO.getLastname().trim());
		employee.setMailAddress(createEmployeeDTO.getMailAddress().trim());
		employee.setPhoneNumber(createEmployeeDTO.getPhoneNumber());
		employee.setRole(role);
		//TODO : generate password using BEncryption
		employee.setPassword(passwordEncoder.encode("Testtest"));
		
		company.getEmployees().add(employee);
		company = this.companyRepository.save(company);
		
		return this.companyMapper.CompanyToCompanyDTO(company);		
	}

}
