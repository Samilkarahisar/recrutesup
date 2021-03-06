package com.polytech.recrutesup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

	private Long id;
	
	private String firstname;

	private String lastname;
	
	private String mailAddress;
	
	private String role;
	
	private String token;

	private String type;
}
