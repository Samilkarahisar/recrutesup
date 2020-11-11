package com.polytech.recrutesup.api;

import com.polytech.recrutesup.dto.OfferDTO;
import com.polytech.recrutesup.dto.StudentDTO;
import com.polytech.recrutesup.services.impl.OfferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("offer")
public class OfferController {

    @Autowired
    private OfferServiceImpl offerService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY') or hasRole('STUDENT')")
    public ResponseEntity<List<OfferDTO>> getAllOffer() {
        return new ResponseEntity<>(this.offerService.getAllOffer(), HttpStatus.OK);
    }

    @GetMapping("/allByCompany/{idCompany}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY')")
    public ResponseEntity<List<OfferDTO>> getAllOfferByCompany(@PathVariable Long idCompany) {
        return new ResponseEntity<>(this.offerService.getAllOfferByCompanyId(idCompany), HttpStatus.OK);
    }

}