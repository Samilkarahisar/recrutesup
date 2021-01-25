package com.polytech.recrutesup.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.polytech.recrutesup.dto.UserDTO;
import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.exceptions.RecruteSupApplicationException;
import com.polytech.recrutesup.exceptions.RecruteSupErrorType;
import com.polytech.recrutesup.mail.service.MailService;
import com.polytech.recrutesup.payload.request.LoginRequest;
import com.polytech.recrutesup.repositories.UserRepository;
import com.polytech.recrutesup.security.jwt.JwtUtils;
import com.polytech.recrutesup.security.services.UserDetailsImpl;
import com.polytech.recrutesup.services.dto.AuthServiceDTO;
import com.polytech.recrutesup.utils.RandomStringUtils;

@Service
public class AuthServiceImpl implements AuthServiceDTO {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailService;

	@Autowired
	private JwtUtils jwtUtils;

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

	@Override
	public void sendPasswordMail(@NotBlank String email) {
		Optional<User> optUser = userRepository.findByMailAddress(email);
		if(!optUser.isPresent()) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.USER_UNKNOWN);
		}
		
		// Génération d'un mot de passe aléatoire de 16 caractères
		RandomStringUtils randomString = new RandomStringUtils(16);
		String mdp = randomString.nextString();
		User user = optUser.get();
		user.setPassword(passwordEncoder.encode(mdp));
		
		user = this.userRepository.save(user);
				
		this.mailService.sendEmailRecuperationPassword(email, mdp);
	}

}
