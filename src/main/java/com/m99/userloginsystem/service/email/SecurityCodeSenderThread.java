package com.m99.userloginsystem.service.email;

import org.apache.commons.mail.EmailException;

import com.m99.userloginsystem.entity.security.EmailSecurityCode;
import com.m99.userloginsystem.mail.templates.SecurityCodeTemplate;

public class SecurityCodeSenderThread extends Thread{

	private EmailSecurityCode emailSecurityCode;
	private EmailSenderDriver emailSenderDriver;

	public SecurityCodeSenderThread(EmailSenderDriver emailSenderDriver, EmailSecurityCode emailSecurityCode) {
		this.emailSecurityCode = emailSecurityCode;
		this.emailSenderDriver = emailSenderDriver;
	}

	@Override
	public void run() {
		SecurityCodeTemplate template = new SecurityCodeTemplate(emailSecurityCode);
		String content = template.getTextContent();
		try {
			//sendMail() returns boolean
			emailSenderDriver.sendMail(emailSecurityCode.getEmail(), "Security Code From M99", content, EmailContentType.SIMPLE_TEXT);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}
