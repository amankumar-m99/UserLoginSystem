package com.m99.userloginsystem.service.email;

import org.apache.commons.mail.EmailException;

import com.m99.userloginsystem.mail.templates.SecurityCodeTemplate;

public class SecurityCodeSenderThread extends Thread{

	private EmailSenderDriver emailSenderDriver;
	private String emailId;
	private int securityCode;

	public SecurityCodeSenderThread(EmailSenderDriver emailSenderDriver, String emailId, int securityCode) {
		this.emailSenderDriver = emailSenderDriver;
		this.emailId = emailId;
		this.securityCode = securityCode;
	}

	@Override
	public void run() {
		SecurityCodeTemplate template = new SecurityCodeTemplate(securityCode, emailId);
		String content = template.getTextContent();
		try {
			boolean sendMail = emailSenderDriver.sendMail(emailId, "Security Code From M99", content, EmailContentType.SIMPLE_TEXT);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}
