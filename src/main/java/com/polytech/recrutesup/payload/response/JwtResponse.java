package com.polytech.recrutesup.payload.response;

public class JwtResponse {

    private String token;
    private String type="Bearer";
    private Long id;
    private String mailAddress;
    private String role;

    /**
     *
     * @param token
     * @param id
     * @param mailAddress
     * @param role
     */
    public JwtResponse(String token, Long id, String mailAddress, String role) {
        this.token = token;
        this.id = id;
        this.mailAddress = mailAddress;
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
}
