package com.polytech.recrutesup.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polytech.recrutesup.dto.WishDTO;
import com.polytech.recrutesup.services.dto.WishServiceDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("wish")
public class WishController {

	@Autowired
	private WishServiceDTO wishServiceDTO;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<WishDTO>> getAllWishes() {
		return new ResponseEntity<>(this.wishServiceDTO.getAllWishes(), HttpStatus.OK);
	}
	
	@PostMapping("/student/{idUser}/{idOffer}")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<WishDTO> createStudentWish(@PathVariable Long idUser, @PathVariable Long idOffer) {
		return new ResponseEntity<>(this.wishServiceDTO.createStudentWish(idUser, idOffer), HttpStatus.CREATED);
	}
	
	@PostMapping("/company/{idCompany}/{idUser}")
	@PreAuthorize("hasRole('COMPANY')")
	public ResponseEntity<WishDTO> createCompanyWish(@PathVariable Long idCompany, @PathVariable Long idUser) {
		return new ResponseEntity<>(this.wishServiceDTO.createCompanyWish(idCompany, idUser), HttpStatus.CREATED);
	}
}