package com.polytech.recrutesup.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.polytech.recrutesup.dto.UserDTO;
import com.polytech.recrutesup.payload.request.LoginRequest;
import com.polytech.recrutesup.security.jwt.JwtUtils;
import com.polytech.recrutesup.security.services.UserDetailsImpl;
import com.polytech.recrutesup.services.dto.AuthServiceDTO;

@Service
public class AuthServiceImpl implements AuthServiceDTO {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Override
	public UserDTO authenticateUser(@NotNull @Valid LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getMailAddress(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return new UserDTO(userDetails.getId(), userDetails.getFirstname(),	userDetails.getLastname(),
				           userDetails.getUsername(), roles.get(0), jwt, "Bearer");
	}

}
