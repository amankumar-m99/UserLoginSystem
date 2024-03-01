package com.m99.userloginsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.entity.User;
import com.m99.userloginsystem.model.EmailVerificationCode;
import com.m99.userloginsystem.model.JwtRequest;
import com.m99.userloginsystem.model.JwtResponse;
import com.m99.userloginsystem.model.UserForm;
import com.m99.userloginsystem.service.JwtService;
import com.m99.userloginsystem.service.UserService;
import com.m99.userloginsystem.service.email.EmailSenderService;
import com.m99.userloginsystem.service.email.EmailVerificationService;

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private JwtService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailVerificationService emailVerificationService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping({"/login"})
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		return userDetailsService.createJwtToken(jwtRequest, authenticationManager);
	}

	@PostMapping({"/enable"})
	public String enableUser(@RequestBody EmailVerificationCode emailVerificationCode) {
		String email = emailVerificationService.activateUserByCode(emailVerificationCode.getCode());
		return email;
	}

	@PostMapping({"/register"})
	public User registerUser(@RequestBody UserForm user) {
		return userService.registerUser(user);
	}
}
