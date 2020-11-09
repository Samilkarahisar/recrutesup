package com.polytech.recrutesup.payload.response;

public class JwtResponse {

    private String token;
    private String type="Bearer";
    private Long id;
    private String mailAddress;
    private String role;
    private String firstname;
    private String lastname;

    public JwtResponse(String token, Long id, String mailAddress, String firstname, String lastname, String role) {
        this.token = token;
        this.id = id;
        this.mailAddress = mailAddress;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
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
}
