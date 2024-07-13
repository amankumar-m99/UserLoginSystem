package com.m99.userloginsystem.service.user;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.entity.security.EmailSecurityCode;
import com.m99.userloginsystem.entity.user.User;
import com.m99.userloginsystem.model.email.EmailForm;
import com.m99.userloginsystem.service.email.EmailSenderService;
import com.m99.userloginsystem.service.email.EmailVerificationService;

@Service
public class PasswordUpdateService {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private EmailVerificationService emailVerificationService;

	public boolean updatePassword(EmailForm emailForm) throws EmailException {
		User user = userService.getUserByUsernameOrEmail(emailForm.getEmail());
		if(user == null) {
			throw new UsernameNotFoundException("No user exists with username or email as '"+ emailForm.getEmail() +"'");
		}
		return sendSecurityCodeForPasswordUpdate(user.getEmail());
	}

	private boolean sendSecurityCodeForPasswordUpdate(String email) throws EmailException {
		EmailSecurityCode emailSecurityCode = emailVerificationService.generateSecurityCodeForEmail(email);
		int securityCode = emailSecurityCode.getSecurityCode();
		boolean isSent = emailSenderService.sendSecurityCode(email, securityCode);
		return isSent;
	}
}
