package com.polytech.recrutesup.payload.request;

import javax.validation.constraints.NotBlank;
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
public class CreateStudentRequest {

	@NotBlank
	@Size(max = 40)
	private String firstname;

	@NotBlank
	@Size(max = 40)
	private String lastname;

	@NotBlank
	@Size(max = 40)
	private String mailAddress;

	@NotBlank
	@Size(max = 40)
	private String schoolYear;
	
	@Size(max = 10)
	private String phoneNumber;
	
	@Size(max = 40)
	private String label;
	
	@Size(max = 500)
	private String description;
}
