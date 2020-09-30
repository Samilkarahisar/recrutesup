package com.polytech.recrutesup.entities;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "User")
public class User implements Serializable {
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@Column(name = "gender", length = 1, nullable = false)
    private String gender;
	
	@Column(name = "username", length = 40, nullable = false)
    private String username;
	
	@Column(name = "mail_address", length = 40, nullable = false)
    private String mailAddress;
	
	@Column(name = "phone_number", length = 10, nullable = true)
    private String phoneNumber;
	
	@Column(name = "password", nullable = false)
    private String password;
    
    public Long getId() {
		return id;
	}

    public void setId(Long _id) {
        this.id = _id;
    }

    public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
