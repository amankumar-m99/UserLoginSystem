package com.m99.userloginsystem.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.dao.UserDao;
import com.m99.userloginsystem.entity.User;
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
		try {
			User user = userDao.findByEmail(username).get();
			if(user == null) {
				user = userDao.findByUsername(username).get();
				if(user == null) {
					throw new UsernameNotFoundException("No user exists with "+ username);
				}
			}
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		throw new UsernameNotFoundException("No user exists with "+ username);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(User user){
		Set<GrantedAuthority> authorities = new HashSet<>();
		user.getRole().forEach(role->{
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		});
		return authorities;
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

	private void authenticate(String username, String password, AuthenticationManager authenticationManager) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch (DisabledException e) {
			throw new Exception("User is disabled");
		}catch (BadCredentialsException e) {
			throw new Exception("Bad credentials");
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
