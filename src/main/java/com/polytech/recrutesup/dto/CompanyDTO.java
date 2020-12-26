package com.polytech.recrutesup.dto;

import java.util.List;

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
public class CompanyDTO {

	private Long id;

	private String name;

	private String mailAddress;

	private String websiteUrl;

	private String description;

	private String state;
	
	private List<OfferDTO> offers;

	private List<EmployeeLightDTO> employees;

	private List<WishDTO> wishSendList;
}
