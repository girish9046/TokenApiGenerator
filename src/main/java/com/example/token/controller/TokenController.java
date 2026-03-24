package com.example.token.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.token.error.UserNotFoundException;
import com.example.token.jwt.JWTUtility;
import com.example.token.jwt.model.User;
import com.example.token.jwt.model.UserService;

@RestController
@RequestMapping("/api/student")
public class TokenController {

	private UserService userService;
	private JWTUtility jWTUtility;

	public TokenController(UserService userService, JWTUtility jWTUtility) {
		this.userService = userService;
		this.jWTUtility = jWTUtility;
	}

	@PostMapping("/register")
	public ResponseEntity<?> postUser(@RequestBody User user) {
		try {
			userService.saveUser(user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password) {
		try {
			System.out.println(username + "........user.getPassword()........." + password);
			if (username == null || password == null) {
				throw new UserNotFoundException("UserName or Password is Empty");
			}
			User user = userService.getUserByNameAndPassword(username, password);

			System.out.println(user.getUserName() + "........etPassword()........." + user.getPassword());
			if (user == null) {
				throw new UserNotFoundException("UserName or Password is Invalid");
			}

			return new ResponseEntity<>(jWTUtility.generateToken(user), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

}
