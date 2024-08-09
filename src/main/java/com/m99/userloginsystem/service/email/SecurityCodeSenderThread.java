package com.m99.userloginsystem.service.email;

import com.m99.userloginsystem.entity.security.SecurityCode;
import com.m99.userloginsystem.mail.templates.SecurityCodeTemplate;

public class SecurityCodeSenderThread extends Thread{

	private SecurityCode securityCode;
	private EmailSenderDriver emailSenderDriver;

	public SecurityCodeSenderThread(EmailSenderDriver emailSenderDriver, SecurityCode securityCode) {
		this.securityCode = securityCode;
		this.emailSenderDriver = emailSenderDriver;
	}

	@Override
	public void run() {
		SecurityCodeTemplate template = new SecurityCodeTemplate(securityCode);
		String content = template.getPlainTextTemplate();
		try {
			//sendMail() returns boolean
			emailSenderDriver.sendMail(securityCode.getEmail(), "Security Code From M99", content, EmailContentType.SIMPLE_TEXT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
