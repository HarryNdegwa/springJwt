package com.example.spring_jwt.demo.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 50L;

    private final String jwtToken;

    public JwtResponse(String token) {
        this.jwtToken = token;
    }

    public String getToken() {
        return this.jwtToken;
    }
}
