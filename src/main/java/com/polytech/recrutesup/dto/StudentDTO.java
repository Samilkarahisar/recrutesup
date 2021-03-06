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
public class StudentDTO {

	private Long id;

	private String firstname;

	private String lastname;

	private String mailAddress;

	private String phoneNumber;

	private String schoolYear;

	private String label;

	private String description;

	private String state;

	private List<WishDTO> wishSendList;

	private List<WishDTO> wishReceivedList;

	private List<AttachmentDTO> attachmentList;
}
