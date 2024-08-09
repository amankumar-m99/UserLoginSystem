package com.m99.userloginsystem.mail.templates;

import com.m99.userloginsystem.entity.security.SecurityCode;

public class SecurityCodeTemplate implements MailTemplate {
	private int securityCode;
	private String recipientEmail;
	private String purpose;

	public SecurityCodeTemplate(SecurityCode securityCode) {
		this.securityCode = securityCode.getSecurityCode();
		this.recipientEmail = securityCode.getEmail();
		this.purpose = securityCode.getPurpose().toString().toLowerCase().replace("_", " ");
	}

	@Override
	public String getHtmlTemplate() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("<p>Hi <strong><a href=\"mailto:%s\">%s</a></strong></p>", recipientEmail, recipientEmail));
		sb.append("<p>We received your request for " +purpose+ ".</p>");
		sb.append("<p>Your one time single-use security code is: <strong>"+securityCode+"</strong></p>");
		sb.append("<p>If you didn't request this code, you can safely ignore this email. Someone else might have typed your email address by mistake.</p>");
		sb.append("<p>Thanks & Regards <br>The LoginSystemAccountTeam <br>Noida, India, 201301<br></p>");
		return sb.toString();
	}

	public String getPlainTextTemplate() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Hi %s,", recipientEmail));
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("We received your request for " +purpose+ ".");
		sb.append(System.lineSeparator());
		sb.append("Your one time single-use security code is: "+securityCode+".");
		sb.append(System.lineSeparator());
		sb.append("If you didn't request this code, you can safely ignore this email. Someone else might have typed your email address by mistake.");
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
