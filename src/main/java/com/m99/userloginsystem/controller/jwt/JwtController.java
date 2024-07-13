package com.m99.userloginsystem.controller.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.model.EmailSecurityCodeForm;
import com.m99.userloginsystem.model.jwt.JwtRequest;
import com.m99.userloginsystem.model.jwt.JwtResponse;
import com.m99.userloginsystem.service.email.EmailVerificationService;
import com.m99.userloginsystem.service.jwt.JwtService;

@RestController
@CrossOrigin
@RequestMapping("/jwt")
public class JwtController {

	@Autowired
	private JwtService userDetailsService;

	@Autowired
	private EmailVerificationService emailVerificationService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping({"/login"})
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		return userDetailsService.createJwtToken(jwtRequest, authenticationManager);
	}

	@PostMapping({"/activate"})
	public String activateUser(@RequestBody EmailSecurityCodeForm emailSecurityCodeForm) {
		String email = emailVerificationService.activateUserBySecurityCode(emailSecurityCodeForm);
		return email;
	}
}
