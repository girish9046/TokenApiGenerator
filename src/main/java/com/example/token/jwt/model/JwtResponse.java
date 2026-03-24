package com.example.token.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class JwtResponse {

	private String jwtToken;
	
    public JwtResponse(String jwtToken) {
		this.setJwtToken(jwtToken);
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	
}
