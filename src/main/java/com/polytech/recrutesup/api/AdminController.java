package com.polytech.recrutesup.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polytech.recrutesup.dto.AdminDTO;
import com.polytech.recrutesup.payload.request.UpdateAdminRequest;
import com.polytech.recrutesup.services.dto.AdminServiceDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private AdminServiceDTO adminServiceDTO;

	@GetMapping("/{idUser}")
	public ResponseEntity<AdminDTO> getAdmin(@PathVariable Long idUser) {
		return new ResponseEntity<>(this.adminServiceDTO.getAdmin(idUser), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<AdminDTO>> getAllAdmins() {
		return new ResponseEntity<>(this.adminServiceDTO.getAllAdmins(), HttpStatus.OK);
	}

	@PatchMapping("/{idUser}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long idUser, @RequestBody UpdateAdminRequest adminDTO) {
		return new ResponseEntity<>(this.adminServiceDTO.updateAdmin(idUser, adminDTO), HttpStatus.OK);
	}
}
