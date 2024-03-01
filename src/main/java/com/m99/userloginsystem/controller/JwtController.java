package com.m99.userloginsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.entity.User;
import com.m99.userloginsystem.model.EmailVerification;
import com.m99.userloginsystem.model.JwtRequest;
import com.m99.userloginsystem.model.JwtResponse;
import com.m99.userloginsystem.model.UserForm;
import com.m99.userloginsystem.service.AccountActivationService;
import com.m99.userloginsystem.service.JwtService;
import com.m99.userloginsystem.service.UserService;
import com.m99.userloginsystem.service.mail.MailSenderService;

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private JwtService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private AccountActivationService accountActivationService;

	@Autowired
	private MailSenderService mailSenderService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping({"/login"})
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		return userDetailsService.createJwtToken(jwtRequest, authenticationManager);
	}

	@PostMapping({"/verify"})
	public boolean verifyEmail(@RequestBody EmailVerification emailVerification) throws Exception {
		String verifyEmailKey = accountActivationService.verifyEmail(emailVerification);
		return mailSenderService.sendActivationMail(emailVerification.getEmail(), verifyEmailKey);
	}

	@PostMapping({"/register"})
	public User registerUser(@RequestBody UserForm user) {
		return userService.registerUser(user);
	}
}
