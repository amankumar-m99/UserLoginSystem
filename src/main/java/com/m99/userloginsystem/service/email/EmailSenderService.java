package com.m99.userloginsystem.service.email;

import java.util.Date;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.entity.User;
import com.m99.userloginsystem.mail.templates.SecurityCodeTemplate;

@Service
public class EmailSenderService {

	@Autowired
	private EmailSender emailSender;

	public boolean sendLoginMail(User user) {
		Date date = new Date();
		String to = user.getEmail();
		String from = "";
		String subject = "Login Alert for SpringBoot LoginSystem";
		String contentText = "An login attempt was made with your account \""+to +"\" on "+date.toString() + ".";
		return true;
	}

	public boolean sendEmailVerificationLink(String email, String verificationCode) {
//		return sendEmail(email, "", "Activation Link", verificationCode);
		return true;
	}

	public boolean sendSecurityCode(String email, int securityCode) throws EmailException {
		SecurityCodeTemplate template = new SecurityCodeTemplate(securityCode, email);
		String content = template.getHtmlContent();
		emailSender.sendHtmlMail(email, "Security Code From M99", content);
		return true;
	}
}
