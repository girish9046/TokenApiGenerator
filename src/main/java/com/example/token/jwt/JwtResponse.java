package com.example.token.jwt;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class JwtResponse {

	private String jwtToken;
	private String message;

}
