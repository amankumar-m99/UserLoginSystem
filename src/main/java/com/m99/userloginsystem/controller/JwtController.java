package com.m99.userloginsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.model.JwtRequest;
import com.m99.userloginsystem.model.JwtResponse;
import com.m99.userloginsystem.security.JwtHelper;
import com.m99.userloginsystem.service.JwtService;

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private JwtService userDetailsService;

	@Autowired
    private JwtHelper helper;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping({"/login"})
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		this.doAuthenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
		String token = this.helper.generateToken(userDetails);
		return new JwtResponse(token, userDetails.getUsername());
//		return userDetailsService.createJwtToken(jwtRequest);
	}

	private void doAuthenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch (DisabledException e) {
			throw new Exception("User is disabled");
		}catch (BadCredentialsException e) {
			throw new Exception("Bad credentials");
		}
	}
}
