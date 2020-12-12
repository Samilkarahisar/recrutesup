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
public class StudentLightDTO {

	private Long id;

	private String firstname;

	private String lastname;

	private String schoolYear;

	private String label;

	private String state;
}
