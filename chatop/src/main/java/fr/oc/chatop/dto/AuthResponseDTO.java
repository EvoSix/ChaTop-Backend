package fr.oc.chatop.dto;

import lombok.*;



public class AuthResponseDTO {
    private String token;
    private long expiresIn;

    public AuthResponseDTO() {


    }


    public AuthResponseDTO(String token, long expiresIn) {
        this.token = token;
this.expiresIn = expiresIn;

    }
    public AuthResponseDTO(String token) {
        this.token = token;


    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public long getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}

