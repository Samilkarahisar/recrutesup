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
@Table(name = "Student_Wish")
public class StudentWish implements Serializable {

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_student", referencedColumnName = "id", nullable = false)
	private Student student;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_offer", referencedColumnName = "id", nullable = false)
	private Offer offer;
	
	@Column(name = "priority_sender", length = 40, nullable = false)
	private int prioritySender;
	
	@Column(name = "priority_receiver", length = 40, nullable = false)
	private int priorityReceiver;
	
	@Column(name = "state", length = 40, nullable = false)
	@Enumerated(EnumType.STRING)
	private WorkflowState state;
}
