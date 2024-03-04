package com.m99.userloginsystem.mail.templates;

import org.springframework.stereotype.Component;

@Component
public class LoginAlertTemplate implements HtmlMailTemplate {

	private String recipientEmail;

	@Override
	public String getHtmlContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("<p>Hi <strong><a href=\"mailto:%s\">%s</a></strong></p>", recipientEmail, recipientEmail));
		sb.append("<p>We received a login request from your account.</p>");
		sb.append("<p>If you didn't request this code, you can safely ignore this email. Someone else might have typed your email address by mistake.</p>");
		sb.append("<p>Thanks & Regards <br>The LoginSystemAccountTeam <br>Noida, India, 201301<br></p>");
		return sb.toString();
	}
}
