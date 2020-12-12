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
public class CreateCompanyRequest {

	@NotBlank
	@Size(max = 40)
	private String name;

	@NotBlank
	@Size(max = 40)
	private String mailAddress;

	@Size(max = 200)
	private String websiteUrl;
	
	@Size(max = 500)
	private String description;
}
