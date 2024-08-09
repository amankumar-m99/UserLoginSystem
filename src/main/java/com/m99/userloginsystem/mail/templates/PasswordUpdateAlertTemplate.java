package com.m99.userloginsystem.mail.templates;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PasswordUpdateAlertTemplate implements MailTemplate {

	private String recipientEmail;

	@Override
	public String getHtmlTemplate() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getPlainTextTemplate() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Hi %s,", recipientEmail));
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("The password for you account is updated.");
		sb.append(System.lineSeparator());
		sb.append("If you are not the one who updated it then someone else might have and the credentials of your account have been compromised.");
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("Thanks & Regards");
		sb.append(System.lineSeparator());
		sb.append("The LoginSystemAccountTeam");
		sb.append(System.lineSeparator());
		sb.append("India, 201301");
		sb.append(System.lineSeparator());
		return sb.toString();
	}

}
