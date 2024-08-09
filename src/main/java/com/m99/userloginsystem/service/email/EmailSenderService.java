package com.m99.userloginsystem.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.entity.security.SecurityCode;

@Service
public class EmailSenderService {

	@Autowired
	private EmailSenderDriver emailSenderDriver;

	public boolean sendSecurityCode(SecurityCode securityCode) {
		return sendSecurityCodeOnAThread(securityCode);
	}

	private boolean sendSecurityCodeOnAThread(SecurityCode securityCode) {
		Thread thread = new SecurityCodeSenderThread(emailSenderDriver, securityCode);
		thread.start();
		return true;	
	}

	public boolean sendLoginAlert(String email) {
		return sendLoginAlertOnAThread(email);
	}

	private boolean sendLoginAlertOnAThread(String email) {
		Thread thread = new LoginAlertSenderThread(emailSenderDriver, email);
		thread.start();
		return true;
	}

	public boolean sendPasswordUpdateAlert(String email) {
		return sendPasswordUpdateAlertOnAThread(email);
	}

	private boolean sendPasswordUpdateAlertOnAThread(String email) {
		Thread thread = new PasswordUpdateAlertSenderThread(emailSenderDriver, email);
		thread.start();
		return true;
	}
}
