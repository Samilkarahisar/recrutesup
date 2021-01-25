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
import com.polytech.recrutesup.dto.EmployeeDTO;
import com.polytech.recrutesup.dto.EmployeeLightDTO;
import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.Role;
import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.entities.reference.ERole;
import com.polytech.recrutesup.entities.reference.EWorkflowState;
import com.polytech.recrutesup.exceptions.RecruteSupApplicationException;
import com.polytech.recrutesup.exceptions.RecruteSupErrorType;
import com.polytech.recrutesup.mail.service.MailService;
import com.polytech.recrutesup.mappers.CompanyMapper;
import com.polytech.recrutesup.payload.request.CreateCompanyRequest;
import com.polytech.recrutesup.payload.request.CreateEmployeeRequest;
import com.polytech.recrutesup.payload.request.LoginRequest;
import com.polytech.recrutesup.repositories.CompanyRepository;
import com.polytech.recrutesup.repositories.RoleRepository;
import com.polytech.recrutesup.services.CompanyService;
import com.polytech.recrutesup.services.dto.CompanyServiceDTO;
import com.polytech.recrutesup.utils.RandomStringUtils;
import com.polytech.recrutesup.utils.WorkflowStateUtils;

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
	
	@Autowired
	private MailService mailService;

	@Override
	public Company findOne(Long id) {
		Optional<Company> company = this.companyRepository.findById(id);
		if (!company.isPresent()) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.COMPANY_UNKNOWN);
		}
		return company.get();
	}

	@Override
	public CompanyDTO createCompany(@NotNull @Valid CreateCompanyRequest createCompanyDTO) {
		Optional<Company> optCompany = companyRepository.findByName(createCompanyDTO.getName());
		if (optCompany.isPresent()) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.COMPANY_ALREADY_CREATED);
		}

		Company company = this.companyMapper.createCompanyRequestToCompany(createCompanyDTO);
		company.setState(EWorkflowState.ENREGISTRE);
		company.setEmployees(new ArrayList<>());
		company.setOffers(new ArrayList<>());
		company.setWishSendList(new ArrayList<>());

		company = this.companyRepository.save(company);
		
		mailService.sendEmailCreationCompanyProfil(company);

		return this.companyMapper.companyToCompanyDTO(company);
	}

	@Override
	public CompanyDTO getCompany(Long idCompany) {
		Company company = this.findOne(idCompany);
		return companyMapper.companyToCompanyDTO(company);
	}
	
	@Override
	public CompanyDTO getCompanyContainingEmployee(@NotNull Long idUser) {
		List<Company> listCompany = this.companyRepository.findAll();
		for (Company company : listCompany) {
			for (User employee : company.getEmployees()) {
				if (employee.getId() == idUser) {
					return companyMapper.companyToCompanyDTO(company);
				}
			}
		}

		throw new RecruteSupApplicationException(RecruteSupErrorType.EMPLOYEE_UNKNOWN);
	}


	@Override
	public List<CompanyDTO> getAllCompanies() {
		List<CompanyDTO> listCompanyDTO = new ArrayList<>();

		List<Company> listCompany = this.companyRepository.findAll();
		listCompany.forEach(company -> listCompanyDTO.add(companyMapper.companyToCompanyDTO(company)));

		return listCompanyDTO;
	}

	@Override
	public CompanyDTO updateCompany(@NotNull Long idCompany, @NotNull @Valid CreateCompanyRequest companyDTO) {
		Company company = this.findOne(idCompany);
		this.companyMapper.updateCompanyFromCreateCompanyRequest(companyDTO, company);
		company = companyRepository.save(company);

		return companyMapper.companyToCompanyDTO(company);
	}
	
	@Override
	public CompanyDTO updateStateCompany(@NotNull Long idCompany, @NotNull String currentState,	@NotNull String nextState) {
		boolean acceptable = WorkflowStateUtils.isNextStateAcceptable(ERole.ROLE_ADMIN, currentState, nextState);
		if(!acceptable) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.UPDATE_STATE_COMPANY_INVALID);
		}
		
		Company company = this.findOne(idCompany);
		if(!currentState.equals(company.getState().toString())) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.STATE_COMPANY_INCORRECT);
		}
		
		company.setState(EWorkflowState.valueOf(nextState));
		
		company = this.companyRepository.save(company);
		return companyMapper.companyToCompanyDTO(company);
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<EmployeeDTO> listEmployeeDTO = new ArrayList<>();

		List<Company> listCompany = this.companyRepository.findAll();
		for (Company company : listCompany) {
			for (User employee : company.getEmployees()) {
				listEmployeeDTO.add(companyMapper.userToEmployeeDTO(employee, company));
			}
		}

		return listEmployeeDTO;
	}

	@Override
	public EmployeeDTO createEmployee(@NotNull @Valid CreateEmployeeRequest createEmployeeDTO) {
		Company company = this.findOne(createEmployeeDTO.getIdCompany());

		User employee = getEmployee(createEmployeeDTO.getMailAddress());
		if (employee != null) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.EMPLOYEE_ALREADY_CREATED);
		}	
		
		Role role = roleRepository.findByName(ERole.ROLE_COMPANY)
				.orElseThrow(() -> new RecruteSupApplicationException(RecruteSupErrorType.ROLE_COMPANY_UNKNOWN));
		employee = new User();
		employee.setFirstname(createEmployeeDTO.getFirstname().trim());
		employee.setLastname(createEmployeeDTO.getLastname().trim());
		employee.setMailAddress(createEmployeeDTO.getMailAddress().trim());
		employee.setPhoneNumber(createEmployeeDTO.getPhoneNumber());
		employee.setRole(role);

		// Génération d'un mot de passe aléatoire de 16 caractères
		RandomStringUtils randomString = new RandomStringUtils(16);
		String mdp = randomString.nextString();
		employee.setPassword(passwordEncoder.encode(mdp));

		company.getEmployees().add(employee);
		company = this.companyRepository.save(company);
		
		employee = getEmployee(employee.getMailAddress());
		
		mailService.sendEmailCreationEmployeeProfil(employee, mdp);
		
		return this.companyMapper.userToEmployeeDTO(employee, company);
	}
	
	private User getEmployee(String mailAddress) {
		List<Company> listCompany = this.companyRepository.findAll();
		for (Company company : listCompany) {
			for (User employee : company.getEmployees()) {
				if (employee.getMailAddress().equals(mailAddress)) {
					return employee;
				}
			}
		}
		
		return null;
	}

	@Override
	public List<EmployeeLightDTO> getAllEmployeesByCompany(@NotNull Long idCompany) {
		List<EmployeeLightDTO> listEmployeeDTO = new ArrayList<>();
		Company company = this.findOne(idCompany);
		for (User employee : company.getEmployees()) {
			listEmployeeDTO.add(companyMapper.userToEmployeeLightDTO(employee));
		}

		return listEmployeeDTO;
	}

	@Override
	public EmployeeDTO getEmployee(@NotNull Long idUser) {
		List<Company> listCompany = this.companyRepository.findAll();
		for (Company company : listCompany) {
			for (User employee : company.getEmployees()) {
				if (employee.getId() == idUser) {
					return companyMapper.userToEmployeeDTO(employee, company);
				}
			}
		}

		throw new RecruteSupApplicationException(RecruteSupErrorType.EMPLOYEE_UNKNOWN);
	}

	@Override
	public EmployeeDTO updateEmployee(@NotNull Long idUser, @NotNull @Valid CreateEmployeeRequest employeeDTO) {
		List<Company> listCompany = this.companyRepository.findAll();
		for (Company company : listCompany) {
			for (User employee : company.getEmployees()) {
				if (employee.getId() == idUser) {
					this.companyMapper.updateEmployeeFromCreateEmployeeRequest(employeeDTO, employee);
					company = companyRepository.save(company);
					return companyMapper.userToEmployeeDTO(employee, company);
				}
			}
		}

		throw new RecruteSupApplicationException(RecruteSupErrorType.EMPLOYEE_UNKNOWN);
	}

	@Override
	public EmployeeDTO changePassword(@NotNull Long idUser, @NotNull @Valid LoginRequest loginRequest) {
		List<Company> listCompany = this.companyRepository.findAll();
		for (Company company : listCompany) {
			for (User employee : company.getEmployees()) {
				if (employee.getId() == idUser) {
					employee.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
					company = companyRepository.save(company);
					mailService.sendEmailConfirmationChangePassword(employee);
					
					return companyMapper.userToEmployeeDTO(employee, company);
				}
			}
		}
		
		throw new RecruteSupApplicationException(RecruteSupErrorType.EMPLOYEE_UNKNOWN);
	}
}
