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
@Table(name = "Meeting")
public class Meeting implements Serializable {

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_student", referencedColumnName = "id")
	private Student student;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_company", referencedColumnName = "id")
	private Company company;
	
	@Column(name = "label", length = 40, nullable = false)
	private String label;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "meeting_date", length = 40, nullable = false)
    private Date meetingDate;
	
	@Column(name = "indicator", length = 40, nullable = false)
	private String indicator;
}
