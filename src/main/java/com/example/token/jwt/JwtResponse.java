package com.example.token.jwt;

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
