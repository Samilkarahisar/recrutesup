package com.polytech.recrutesup.dto;

import com.polytech.recrutesup.entities.reference.EWorkflowState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/***
 * Use this class in case you need a lighter Offer
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferLightDTO {

    private Long id;

    private String label;

    private String city;

    private Date creationDate;

    private EWorkflowState state;

    private String companyName;
    
}
