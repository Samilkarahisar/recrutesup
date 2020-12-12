package com.polytech.recrutesup.payload.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOfferRequest {

	@NotBlank
	@Size(max = 40)
	private String label;

	@Size(max = 500)
	private String description;

	@NotBlank
	@Size(max = 40)
	private String address;

	@NotBlank
	@Size(max = 40)
	private String city;

	@NotBlank
	@Size(max = 40)
	private String mailAddress;

	private List<String> attachmentNamesList;

	@NotNull
	private Long userId;
}
