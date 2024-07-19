package com.m99.userloginsystem.service.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.entity.smtp.Smtp;
import com.m99.userloginsystem.service.smtp.SmtpService;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderDriver {

	@Autowired
	private SmtpService smtpService;

	private Smtp smtpData;
	private Session session;
	private Properties smtpProperties;

	private void initData() {
		if(smtpData == null) {
			smtpData = this.smtpService.getById(1);
		}
		if(smtpProperties == null) {
			smtpProperties = new Properties();
			smtpProperties.put("mail.smtp.auth", smtpData.getAuth());
			smtpProperties.put("mail.smtp.starttls.enable", smtpData.getStarttlsEnable());
			smtpProperties.put("mail.smtp.port", smtpData.getPort());
			smtpProperties.put("mail.smtp.host", smtpData.getHost());
		}
		String password = smtpData.getPassword();
		session = Session.getInstance(smtpProperties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(smtpData.getUsername(), password);
			}
		});
	}

	public boolean sendMail(String recipientEmail, String subject, String content, EmailContentType contentType) {
		initData();
		switch (contentType) {
		case SIMPLE_TEXT:
			return sendTextMail(recipientEmail, subject, content);
		case HTML:
			return sendTextMail(recipientEmail, subject, content);
		}
		return false;
	}

	private boolean sendTextMail(String recipientEmail, String subject, String content) {
		boolean flag = false;
		try {
			Message message = new MimeMessage(session);
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			message.setFrom(new InternetAddress(smtpData.getUsername()));
			message.setSubject(subject);
			message.setText(content);
			Transport.send(message);
			flag = true;
		}catch (Exception e) {
		}
		return flag;
	}
}
