package com.polytech.recrutesup.dto;

import java.util.Date;
import java.util.List;

import com.polytech.recrutesup.entities.reference.EWorkflowState;

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
public class OfferDTO {

	private Long id;

	private String label;

	private String description;

	private String address;

	private String city;

	private String mailAddress;

	private Date creationDate;

	private String userFirstname;

	private String userLastname;

	private EWorkflowState state;

	private Long companyId;

	private String companyName;

	private List<AttachmentDTO> attachmentList;

	private List<WishDTO> wishReceivedList;

}
