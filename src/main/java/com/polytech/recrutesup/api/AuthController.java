package com.polytech.recrutesup.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polytech.recrutesup.dto.UserDTO;
import com.polytech.recrutesup.payload.request.LoginRequest;
import com.polytech.recrutesup.services.dto.AuthServiceDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	AuthServiceDTO authServiceDTO;

	@PostMapping("/signin")
	public ResponseEntity<UserDTO> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return new ResponseEntity<>(this.authServiceDTO.authenticateUser(loginRequest), HttpStatus.OK);
	}
	
	@PostMapping("/forgottenPW/{email}")
	public void sendPasswordMail(@PathVariable String email) {
		this.authServiceDTO.sendPasswordMail(email);
	}
}
