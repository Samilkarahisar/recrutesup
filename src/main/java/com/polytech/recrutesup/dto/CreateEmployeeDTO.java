package com.polytech.recrutesup.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class CreateEmployeeDTO {

	@NotNull
	@Size(max = 40)
	private String firstname;
	
	@NotNull
	@Size(max = 40)
	private String lastname;
	
	@NotNull
	@Size(max = 40)
	private String mailAddress;
	
	@Size(max = 40)
	private String phoneNumber;
	
	@NotNull
	private Long idCompany;
}
