package com.m99.userloginsystem.service.email;

import com.m99.userloginsystem.mail.templates.LoginAlertTemplate;

public class LoginAlertSenderThread extends Thread{
	private EmailSenderDriver emailSenderDriver;
	private String recipientEmail;

	public LoginAlertSenderThread(EmailSenderDriver emailSenderDriver, String recipientEmail) {
		this.emailSenderDriver = emailSenderDriver;
		this.recipientEmail = recipientEmail;
	}

	@Override
	public void run() {
		LoginAlertTemplate template = new LoginAlertTemplate(recipientEmail);
		String content = template.getPlainTextTemplate();
		try {
			//sendMail() returns boolean
			emailSenderDriver.sendMail(recipientEmail, "Login Alert", content, EmailContentType.SIMPLE_TEXT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
