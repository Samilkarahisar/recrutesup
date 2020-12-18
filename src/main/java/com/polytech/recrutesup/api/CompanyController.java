package com.polytech.recrutesup.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polytech.recrutesup.dto.CompanyDTO;
import com.polytech.recrutesup.dto.EmployeeDTO;
import com.polytech.recrutesup.dto.EmployeeLightDTO;
import com.polytech.recrutesup.payload.request.CreateCompanyRequest;
import com.polytech.recrutesup.payload.request.CreateEmployeeRequest;
import com.polytech.recrutesup.payload.request.LoginRequest;
import com.polytech.recrutesup.services.dto.CompanyServiceDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	private CompanyServiceDTO companyServiceDTO;

	// COMPANY

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
	public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
		return new ResponseEntity<>(this.companyServiceDTO.getAllCompanies(), HttpStatus.OK);
	}

	@GetMapping("/{idCompany}")
	public ResponseEntity<CompanyDTO> getCompany(@PathVariable Long idCompany) {
		return new ResponseEntity<>(this.companyServiceDTO.getCompany(idCompany), HttpStatus.OK);
	}
	
	@GetMapping("/byemployee/{idUser}")
	@PreAuthorize("hasRole('COMPANY')")
	public ResponseEntity<CompanyDTO> getCompanyContainingEmployee(@PathVariable Long idUser) {
		return new ResponseEntity<>(this.companyServiceDTO.getCompanyContainingEmployee(idUser), HttpStatus.OK);
	}

	@PatchMapping("/{idCompany}")
	@PreAuthorize("hasRole('COMPANY')")
	public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long idCompany, @Valid @RequestBody CreateCompanyRequest companyDTO) {
		return new ResponseEntity<>(this.companyServiceDTO.updateCompany(idCompany, companyDTO), HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CompanyDTO> createCompany(@RequestBody CreateCompanyRequest createCompanyDTO) {
		return new ResponseEntity<>(this.companyServiceDTO.createCompany(createCompanyDTO), HttpStatus.CREATED);
	}

	// EMPLOYEE

	@GetMapping("/employee/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		return new ResponseEntity<>(this.companyServiceDTO.getAllEmployees(), HttpStatus.OK);
	}

	@GetMapping("/employee/all/{idCompany}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<EmployeeLightDTO>> getAllEmployeesByCompany(@PathVariable Long idCompany) {
		return new ResponseEntity<>(this.companyServiceDTO.getAllEmployeesByCompany(idCompany), HttpStatus.OK);
	}

	@GetMapping("/employee/{idUser}")
	@PreAuthorize("hasRole('COMPANY')")
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long idUser) {
		return new ResponseEntity<>(this.companyServiceDTO.getEmployee(idUser), HttpStatus.OK);
	}

	@PatchMapping("/employee/{idUser}")
	@PreAuthorize("hasRole('COMPANY')")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long idUser, @RequestBody CreateEmployeeRequest employeeDTO) {
		return new ResponseEntity<>(this.companyServiceDTO.updateEmployee(idUser, employeeDTO), HttpStatus.OK);
	}

	@PostMapping("/employee")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeDTO) {
		return new ResponseEntity<>(this.companyServiceDTO.createEmployee(createEmployeeDTO), HttpStatus.CREATED);
	}
	
	@PatchMapping("/changePW/{idUser}")
	@PreAuthorize("hasRole('COMPANY')")
	public ResponseEntity<EmployeeDTO> changePassword(@PathVariable Long idUser, @Valid @RequestBody LoginRequest loginRequest) {
		return new ResponseEntity<>(this.companyServiceDTO.changePassword(idUser, loginRequest), HttpStatus.OK);
	}
}
