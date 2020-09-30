package com.polytech.recrutesup.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", length = 40, nullable = false)
    private Date creationDate;
	
	@Column(name = "creation_username", length = 40, nullable = false)
    private User creationUsername;
	
	@Column(name = "indicator", length = 40, nullable = false)
    private String indicator;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_company", referencedColumnName = "id")
	private Company company;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_attachment", referencedColumnName = "id")
	private CompanyAttachment attachment;
	
	
}
