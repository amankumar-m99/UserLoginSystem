package com.m99.userloginsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.dao.UserDao;
import com.m99.userloginsystem.entity.user.User;
import com.m99.userloginsystem.model.JwtRequest;
import com.m99.userloginsystem.model.JwtResponse;
import com.m99.userloginsystem.security.JwtHelper;

@Service
public class JwtService implements UserDetailsService{

	@Autowired
	private UserDao userDao;

	@Autowired
	private JwtHelper jwtHelper;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userDao.findByEmail(username).orElse(null);
		if(user == null) {
			user = userDao.findByUsername(username).get();
			if(user == null) {
				throw new UsernameNotFoundException("No user exists with username or email as '"+ username +"'");
			}
		}
		return user;
	}

	public JwtResponse createJwtToken(JwtRequest jwtRequest, AuthenticationManager authenticationManager) throws Exception {
		String userName = jwtRequest.getEmail();
		String password = jwtRequest.getPassword();
		authenticate(userName, password, authenticationManager);
		final UserDetails userDetails = loadUserByUsername(userName);
		String generateToken = jwtHelper.generateToken(userDetails);
		User user = userDao.findByEmail(userName).get();
		return new JwtResponse(generateToken, user.getUsername());
	}

	private void authenticate(String username, String password, AuthenticationManager authenticationManager) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch (DisabledException e) {
			throw new DisabledException("User is disabled");
		}catch (LockedException e) {
			throw new LockedException("Account is locked!");
		}catch (BadCredentialsException e) {
			throw new BadCredentialsException("Bad credentials");
		}
	}
}
