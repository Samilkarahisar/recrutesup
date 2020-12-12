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
public class EmployeeDTO {

	private Long id;

	private String firstname;

	private String lastname;

	private String mailAddress;

	private String phoneNumber;

	private Long idCompany;

	private String companyName;
}
