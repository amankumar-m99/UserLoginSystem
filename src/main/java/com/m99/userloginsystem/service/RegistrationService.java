package com.m99.userloginsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.dao.user.UserDao;
import com.m99.userloginsystem.entity.user.User;
import com.m99.userloginsystem.utils.Validator;

@Service
public class RegistrationService {

	@Autowired
	private UserDao userDao;

	public boolean isUsernameAvailable(String username) {
		if(username == null || username.trim().isEmpty())
			return false;
		if(username.equalsIgnoreCase("admin")) {
			return false;
		}
		User user = userDao.findByUsername(username).orElse(null);
		if(user!=null)
			return false;
		return true;
	}

	public boolean isEmailInUse(String email) {
		if(!validateEmailChecks(email)) {
			return false;
		}
		User user = userDao.findByEmail(email).orElse(null);
		if(user!=null)
			return true;
		return false;
	}

	public boolean hasEmailBeenInUse(String email) {
		return false;
	}

	public boolean validateEmailChecks(String email) {
		if(email == null || email.trim().isEmpty()) {
			return false;
		}
		if(!Validator.isValidEmail(email)) {
			return false;
		}
		return true;
	}
}
