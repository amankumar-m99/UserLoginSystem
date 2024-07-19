package com.m99.userloginsystem.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.entity.security.EmailSecurityCode;

@Service
public class EmailSenderService {

	@Autowired
	private EmailSenderDriver emailSenderDriver;

	public boolean sendSecurityCode(EmailSecurityCode emailSecurityCode) {
		return sendSecurityCodeOnAThread(emailSecurityCode);
	}

	private boolean sendSecurityCodeOnAThread(EmailSecurityCode emailSecurityCode) {
		Thread thread = new SecurityCodeSenderThread(emailSenderDriver, emailSecurityCode);
		thread.start();
		return true;	
	}
}
