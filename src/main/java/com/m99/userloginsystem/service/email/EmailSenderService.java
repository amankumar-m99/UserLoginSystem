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
}
