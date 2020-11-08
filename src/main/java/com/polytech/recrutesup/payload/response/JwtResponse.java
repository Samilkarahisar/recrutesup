package com.polytech.recrutesup.payload.response;


public class JwtResponse {

	private Long id;
	private String mailAddress;
	private String firstname;
	private String lastname;
	private String role;
    private String token;
    private String type="Bearer";

    /**
     *
     * @param token
     * @param id
     * @param mailAddress
     * @param role
     */
    public JwtResponse(String token, Long id, String mailAddress, String firstname, String lastname, String role) {
        this.token = token;
        this.id = id;
        this.mailAddress = mailAddress;
        this.role = role;
    }

    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getRole() {
        return this.role;
    }
}
