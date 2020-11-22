package com.polytech.recrutesup.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.polytech.recrutesup.entities.reference.EWorkflowState;

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
@Table(name = "Company")
public class Company implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name", length = 40, nullable = false)
	private String name;

	@Column(name = "mail_address", length = 40, nullable = false)
	private String mailAddress;

	@Column(name = "website_url", length = 200, nullable = true)
	private String websiteUrl;

	@Column(name = "description", length = 500, nullable = true)
	private String description;

	@Column(name = "state", length = 40, nullable = false)
	@Enumerated(EnumType.STRING)
	private EWorkflowState state;

	@JoinTable(name = "company_employee",
			   joinColumns = @JoinColumn(name = "id_company", referencedColumnName = "id"),
			   inverseJoinColumns = @JoinColumn(name = "id_employee", referencedColumnName = "id"))
	@OneToMany(cascade = CascadeType.ALL)
	private List<User> employees;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "company")
	private List<CompanyWish> wishSendList;

}
