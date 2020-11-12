package com.polytech.recrutesup.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polytech.recrutesup.dto.CompanyDTO;
import com.polytech.recrutesup.dto.CreateCompanyDTO;
import com.polytech.recrutesup.dto.CreateEmployeeDTO;
import com.polytech.recrutesup.services.dto.CompanyServiceDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	private CompanyServiceDTO companyServiceDTO;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
	public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
		return new ResponseEntity<>(this.companyServiceDTO.getAllCompanies(), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CompanyDTO> createCompany(@RequestBody CreateCompanyDTO createCompanyDTO) {
		return new ResponseEntity<>(this.companyServiceDTO.createCompany(createCompanyDTO), HttpStatus.CREATED);
	}
	
	@PostMapping("/employee")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CompanyDTO> createEmployee(@RequestBody CreateEmployeeDTO createEmployeeDTO) {
		return new ResponseEntity<>(this.companyServiceDTO.createEmployee(createEmployeeDTO), HttpStatus.CREATED);
	}

}
