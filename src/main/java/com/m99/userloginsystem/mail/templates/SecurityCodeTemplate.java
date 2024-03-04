package com.m99.userloginsystem.mail.templates;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityCodeTemplate implements HtmlMailTemplate {
	private int securityCode;
	private String recipientEmail;

	@Override
	public String getHtmlContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("<p>Hi <strong><a href=\"mailto:%s\">%s</a></strong></p>", recipientEmail, recipientEmail));
		sb.append("<p>We received your request for a single-use code to use with your account.</p>");
		sb.append("<p>Your one time security code is: <strong>"+securityCode+"</strong></p>");
		sb.append("<p>If you didn't request this code, you can safely ignore this email. Someone else might have typed your email address by mistake.</p>");
		sb.append("<p>Thanks & Regards <br>The LoginSystemAccountTeam <br>Noida, India, 201301<br></p>");
		return sb.toString();
	}
}
