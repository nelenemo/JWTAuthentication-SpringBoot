package com.example.security.dto;

public class AuthenticatonResponse {
    private final String jwt;

    public AuthenticatonResponse(String jwt) {
        this.jwt = jwt;
    }
    public String getJwt(){return jwt;}
}
