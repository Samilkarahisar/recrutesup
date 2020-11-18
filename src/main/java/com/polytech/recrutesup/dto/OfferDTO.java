package com.polytech.recrutesup.dto;

import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.Attachment;
import com.polytech.recrutesup.entities.reference.EWorkflowState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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

    private Company company;

    private List<Attachment> attachmentList;

}
