package com.m99.userloginsystem.service.email;

import java.util.Date;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.entity.user.User;
import com.m99.userloginsystem.mail.templates.SecurityCodeTemplate;

@Service
public class EmailSenderService {

	@Autowired
	private EmailSenderDriver emailSenderDriver;

	public boolean sendLoginMail(User user) {
		Date date = new Date();
		String to = user.getEmail();
		String from = "";
		String subject = "Login Alert for SpringBoot LoginSystem";
		String contentText = "An login attempt was made with your account \""+to +"\" on "+date.toString() + ".";
		return true;
	}

	public boolean sendSecurityCode(String emailId, int securityCode) throws EmailException {
		return sendSecurityCodeOnAThread(emailId, securityCode);
	}

	private boolean sendSecurityCodeOnAThread(String emailId, int securityCode) {
		Thread thread = new SecurityCodeSenderThread(emailSenderDriver, emailId, securityCode);
		thread.start();
		return true;	
	}
}
