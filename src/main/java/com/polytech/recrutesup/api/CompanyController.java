package com.polytech.recrutesup.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polytech.recrutesup.dto.CompanyDTO;
import com.polytech.recrutesup.dto.CreateCompanyDTO;
import com.polytech.recrutesup.services.dto.CompanyServiceDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	private CompanyServiceDTO companyServiceDTO;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CompanyDTO> createCompany(@RequestBody CreateCompanyDTO createCompanyDTO) {
		return new ResponseEntity<>(this.companyServiceDTO.createCompany(createCompanyDTO), HttpStatus.OK);
	}

}
