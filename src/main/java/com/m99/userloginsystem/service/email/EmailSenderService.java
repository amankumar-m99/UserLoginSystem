package com.m99.userloginsystem.service.email;

import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.entity.Smtp;
import com.m99.userloginsystem.entity.User;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService {

	@Autowired
	private SmtpService smtpService;

	private boolean sendEmail(String to, String from, String subject, String text) {
		boolean flag = true;
		Smtp smtpData = smtpService.getById(1);
		Properties properties = new Properties();
		//smtp properties
		properties.put("mail.smtp.auth", smtpData.getAuth());
		properties.put("mail.smtp.starttls.enable", smtpData.getStarttlsEnable());
		properties.put("mail.smtp.port", smtpData.getPort());
		properties.put("mail.smtp.host", smtpData.getHost());

		String username = smtpData.getUsername();
		String password = smtpData.getPassword();
		//session
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress(username));
			message.setSubject(subject);
			message.setText(text);
			System.out.println("Sending mail to "+to);
			Transport.send(message);
			System.out.println("Mail sent to "+to);
			flag = true;
		}catch (Exception e) {
			System.out.println("Mail sending failed to "+to);
		}
		return flag;
	}

	public boolean sendLoginMail(User user) {
		Date date = new Date();
		String to = user.getEmail();
		String from = "";
		String subject = "Login Alert for SpringBoot LoginSystem";
		String contentText = "An login attempt was made with your account \""+to +"\" on "+date.toString() + ".";
		return sendEmail(to, from, subject, contentText);
	}

	public boolean sendEmailVerificationLink(String email, String verificationCode) {
		return sendEmail(email, "", "Activation Link", verificationCode);
	}
}
