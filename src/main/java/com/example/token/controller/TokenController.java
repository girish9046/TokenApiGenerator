package com.example.token.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.token.error.UserNotFoundException;
import com.example.token.jwt.JWTUtility;
import com.example.token.jwt.JwtResponse;
import com.example.token.jwt.model.User;
import com.example.token.jwt.model.UserService;

@RestController
@RequestMapping("/api/student")
public class TokenController {

	private UserService userService;
	private JWTUtility jWTUtility;
	private JwtResponse jwtResponse;

	public TokenController(UserService userService, JWTUtility jWTUtility, JwtResponse jwtResponse) {
		this.userService = userService;
		this.jWTUtility = jWTUtility;
		this.jwtResponse = jwtResponse;
	}


	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> postUser(@RequestBody User user) {
		try {
			userService.saveUser(user);
			return new ResponseEntity<Object>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}


	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JwtResponse> loginUser(@RequestParam String username, @RequestParam String password) {
		try {
			System.out.println(username + "........user.getPassword()........." + password);
			if (username == null || password == null) {
				throw new UserNotFoundException("UserName or Password is Empty");
			}
			User user = userService.getUserByNameAndPassword(username, password);

			System.out.println(user.getUserName() + "........etPassword()........." + user.getPassword());
			jwtResponse.setMessage("");
			jwtResponse.setJwtToken(jWTUtility.generateToken(user));

			return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			jwtResponse.setMessage("UserName or Password is Invalid");
			jwtResponse.setJwtToken("");
			return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.CONFLICT);
		}
	}

}
