package com.polytech.recrutesup.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student implements Serializable {

	@Id
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
	private User user;
	
	@Column(name = "school_year", length = 1, nullable = false)
	private int schoolYear;
	
	@Column(name = "label", length = 40, nullable = false)
	private String label;
	
	@Column(name = "description", length = 500, nullable = true)
	private String description;
	
	@Column(name = "indicator", length = 40, nullable = false)
	private String indicator;
	
	@Column(name = "label_picture", length = 40, nullable = true)
	private String labelPicture;
}
