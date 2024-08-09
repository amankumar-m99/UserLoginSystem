package com.m99.userloginsystem.service.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.entity.user.User;
import com.m99.userloginsystem.model.jwt.JwtRequest;
import com.m99.userloginsystem.model.jwt.JwtResponse;
import com.m99.userloginsystem.security.jwt.JwtHelper;
import com.m99.userloginsystem.service.email.EmailSenderService;
import com.m99.userloginsystem.service.user.UserService;

@Service
public class JwtService implements UserDetailsService{

	@Autowired
	private UserService userService;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private JwtHelper jwtHelper;

	public JwtResponse createJwtToken(JwtRequest jwtRequest, AuthenticationManager authenticationManager) throws Exception {
		String username = jwtRequest.getEmail();
		String password = jwtRequest.getPassword();
		User user = (User) loadUserByUsername(username);
		authenticate(username, password, authenticationManager);
		if(user.getSecurityDetails().getLoginAlert()) {
			sendLoginAlert(user.getEmail());
		}
		String generateToken = jwtHelper.generateToken(user);
		return new JwtResponse(user.getId(), user.getEmail(), generateToken);
	}

	private void sendLoginAlert(String email) {
		emailSenderService.sendLoginAlert(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userService.getUserByUsernameOrEmail(username);
		return user;
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
