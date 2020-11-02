package com.polytech.recrutesup.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.polytech.recrutesup.entities.reference.WorkflowState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Offer")
public class Offer implements Serializable {

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@Column(name = "label", length = 40, nullable = false)
    private String label;
	
	@Column(name = "description", length = 500, nullable = true)
    private String description;
	
	@Column(name = "address", length = 40, nullable = false)
    private String address;
	
	@Column(name = "city", length = 40, nullable = false)
    private String city;
	
	@Column(name = "mailAddress", length = 40, nullable = false)
    private String mailAddress;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", length = 40, nullable = false, updatable = false)
    private Date creationDate;
	
	@Column(name = "creation_username", length = 40, nullable = false, updatable = false)
    private User creationUsername;
	
	@Column(name = "state", length = 40, nullable = false)
	@Enumerated(EnumType.STRING)
	private WorkflowState state;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_company", referencedColumnName = "id", nullable = false)
	private Company company;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_attachment", referencedColumnName = "id", nullable = true)
	private OfferAttachment attachment;
}
