package com.polytech.recrutesup.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "User")
public class User implements Serializable {
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@Column(name = "username", length = 40, nullable = false)
    private String username;
	
	@Column(name = "mail_address", length = 40, nullable = false)
    private String mailAddress;
	
	@Column(name = "phone_number", length = 10, nullable = true)
    private String phoneNumber;
	
	@Column(name = "password", nullable = false)
    private String password;
}
