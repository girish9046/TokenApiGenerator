/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.token.jwt.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;



/**
 *
 * @author Namratha
 */
@Component
@Setter
@Getter
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Login  {

	//@NotEmpty(message = "{userName.required}") // @Size(min=2, max=30)
	private String userName="";
//	@NotEmpty(message = "{password.required}") // @Min(5)
	private String password="";
	private String token = "";


}
