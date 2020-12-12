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

import com.polytech.recrutesup.dto.OfferDTO;
import com.polytech.recrutesup.dto.OfferLightDTO;
import com.polytech.recrutesup.payload.request.CreateOfferRequest;
import com.polytech.recrutesup.services.dto.OfferServiceDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("offer")
public class OfferController {

	@Autowired
	private OfferServiceDTO offerServiceDTO;

	@GetMapping("/{idOffer}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY') or hasRole('STUDENT')")
	public ResponseEntity<OfferDTO> getOffer(@PathVariable Long idOffer) {
		return new ResponseEntity<>(this.offerServiceDTO.getOffer(idOffer), HttpStatus.OK);
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY') or hasRole('STUDENT')")
	public ResponseEntity<List<OfferDTO>> getAllOffers() {
		return new ResponseEntity<>(this.offerServiceDTO.getAllOffer(), HttpStatus.OK);
	}

	@GetMapping("/all/{idCompany}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY')")
	public ResponseEntity<List<OfferDTO>> getAllOffersByCompany(@PathVariable Long idCompany) {
		return new ResponseEntity<>(this.offerServiceDTO.getAllOfferByCompanyId(idCompany), HttpStatus.OK);
	}

	@GetMapping("/light/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY') or hasRole('STUDENT')")
	public ResponseEntity<List<OfferLightDTO>> getAllOffersLight() {
		return new ResponseEntity<>(this.offerServiceDTO.getAllOfferLight(), HttpStatus.OK);
	}

	@GetMapping("/light/all/{idCompany}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY') or hasRole('STUDENT')")
	public ResponseEntity<List<OfferLightDTO>> getAllOfferLightByCompany(@PathVariable Long idCompany) {
		return new ResponseEntity<>(this.offerServiceDTO.getAllOfferByCompanyIdLight(idCompany), HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasRole('COMPANY')")
	public ResponseEntity<OfferDTO> createOffer(@Valid @RequestBody CreateOfferRequest createOfferRequest) {
		return new ResponseEntity<>(this.offerServiceDTO.createOffer(createOfferRequest), HttpStatus.CREATED);
	}
	
	@PatchMapping("/{idOffer}")
	@PreAuthorize("hasRole('COMPANY')")
	public ResponseEntity<OfferDTO> updateOffer(@PathVariable Long idOffer, @RequestBody CreateOfferRequest offerDTO) {
		return new ResponseEntity<>(this.offerServiceDTO.updateOffer(idOffer, offerDTO), HttpStatus.OK);
	}
}
