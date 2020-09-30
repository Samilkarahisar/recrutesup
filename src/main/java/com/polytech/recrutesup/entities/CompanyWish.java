package com.polytech.recrutesup.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Company_Wish")
public class CompanyWish implements Serializable {

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_company", referencedColumnName = "id")
	private Company company;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_offer", referencedColumnName = "id")
	private Offer offer;
	
	@Column(name = "priority", length = 40, nullable = false)
	private int priority;
	
	@Column(name = "indicator", length = 40, nullable = false)
	private String indicator;
}
