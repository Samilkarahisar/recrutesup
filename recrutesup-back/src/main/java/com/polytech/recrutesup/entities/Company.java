package com.polytech.recrutesup.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Company")
public class Company implements Serializable {

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@Column(name = "name", length = 40, nullable = false)
    private String name;
	
	@Column(name = "address", length = 40, nullable = false)
    private String address;
	
	@Column(name = "mailAddress", length = 40, nullable = false)
    private String mailAddress;
	
	@Column(name = "description", length = 500, nullable = true)
    private String description;
	
	@Column(name = "indicator", length = 40, nullable = false)
    private String indicator;
	
	@Column(name = "label_logo", length = 40, nullable = true)
	private String labelLogo;
	
	@JoinTable(name = "CompanyEmployee",
	           joinColumns = @JoinColumn(name = "idCompany", referencedColumnName = "id"),
	           inverseJoinColumns = @JoinColumn(name = "idEmployee", referencedColumnName = "id"))
	@OneToMany
	private List<User> employees;
	
}
