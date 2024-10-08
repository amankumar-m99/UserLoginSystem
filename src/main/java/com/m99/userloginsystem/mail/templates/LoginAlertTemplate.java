package com.m99.userloginsystem.mail.templates;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginAlertTemplate implements MailTemplate {

	private String recipientEmail;

	@Override
	public String getHtmlTemplate() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("<p>Hi <strong><a href=\"mailto:%s\">%s</a></strong></p>", recipientEmail, recipientEmail));
		sb.append("<p>We received a login request from your account.</p>");
		sb.append("<p>If you didn't request this code, you can safely ignore this email. Someone else might have typed your email address by mistake.</p>");
		sb.append("<p>Thanks & Regards <br>The LoginSystemAccountTeam <br>Noida, India, 201301<br></p>");
		return sb.toString();
	}

	@Override
	public String getPlainTextTemplate() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Hi %s,", recipientEmail));
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("One device is logged in to your account.");
		sb.append(System.lineSeparator());
		sb.append("If you are not the one who logged in then someone else might have and the credentials of your account have been compromised.");
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
