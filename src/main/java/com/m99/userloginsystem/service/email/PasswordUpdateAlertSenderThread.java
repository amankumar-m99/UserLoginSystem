package com.m99.userloginsystem.service.email;

import com.m99.userloginsystem.mail.templates.PasswordUpdateAlertTemplate;

public class PasswordUpdateAlertSenderThread extends Thread{
	private EmailSenderDriver emailSenderDriver;
	private String recipientEmail;

	public PasswordUpdateAlertSenderThread(EmailSenderDriver emailSenderDriver, String recipientEmail) {
		this.emailSenderDriver = emailSenderDriver;
		this.recipientEmail = recipientEmail;
	}

	@Override
	public void run() {
		PasswordUpdateAlertTemplate template = new PasswordUpdateAlertTemplate(recipientEmail);
		String content = template.getPlainTextTemplate();
		try {
			//sendMail() returns boolean
			emailSenderDriver.sendMail(recipientEmail, "Password Update Alert", content, EmailContentType.SIMPLE_TEXT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
