package com.m99.userloginsystem.mail.templates;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AccountActivationTemplate implements HtmlMailTemplate{

	String recipientEmail;
	private String link;

	@Override
	public String getHtmlContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("<p>Hi <strong><a href=\"mailto:%s\">%s</a></strong></p>", recipientEmail, recipientEmail));
		sb.append("<p>We received your request to activate your account.</p>");
		sb.append(String.format("<p>Follow this <strong><a href=\"%s\">link to activate</a></strong> your account.</p>", link));
		sb.append("<p>This link can be used one time only and will expire after 24 hours if unused.</p>");
	    sb.append("<p>Thanks & Regards <br>The LoginSystemAccountTeam <br>Noida, India, 201301<br></p>");
		return sb.toString();
	}

}
