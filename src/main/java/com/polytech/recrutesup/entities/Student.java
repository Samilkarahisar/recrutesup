package com.polytech.recrutesup.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

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
@Table(name = "Student")
public class Student implements Serializable {

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
	private User user;
	
	@Column(name = "school_year", length = 40, nullable = false)
	private String schoolYear;
	
	@Column(name = "label", length = 40, nullable = true)
	private String label;
	
	@Column(name = "description", length = 500, nullable = true)
	private String description;
	
	@Column(name = "state", length = 40, nullable = false)
	@Enumerated(EnumType.STRING)
	private WorkflowState state;
	
	@Column(name = "label_picture", length = 100, nullable = true)
	private String labelPicture;
}
