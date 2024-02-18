package com.m99.userloginsystem.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) {
		return null;
	}

}
