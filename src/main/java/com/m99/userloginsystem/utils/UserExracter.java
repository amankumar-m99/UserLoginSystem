package com.m99.userloginsystem.utils;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.m99.userloginsystem.entity.user.User;

import jakarta.servlet.http.HttpServletRequest;

public class UserExracter {
	public static long getUserIdFromRequest(HttpServletRequest request) {
		Principal userPrincipal = request.getUserPrincipal();
		if(userPrincipal instanceof UsernamePasswordAuthenticationToken) { 
			UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)userPrincipal;
			if(token.getPrincipal() instanceof User) {
				User user = (User)(token.getPrincipal());
				return user.getId();
			}
		}
		return -1;
	}
}
