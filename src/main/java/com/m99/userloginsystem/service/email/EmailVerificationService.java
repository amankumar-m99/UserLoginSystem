package com.m99.userloginsystem.service.email;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.dao.EmailSecurityCodeDao;
import com.m99.userloginsystem.dao.EmailVerificationDao;
import com.m99.userloginsystem.entity.EmailSecurityCode;
import com.m99.userloginsystem.entity.EmailVerification;
import com.m99.userloginsystem.model.EmailForm;
import com.m99.userloginsystem.utils.OtpGenerator;

@Service
public class EmailVerificationService {

	@Autowired
	private EmailVerificationDao emailVerificationDao;

	private EmailSecurityCodeDao emailSecurityCodeDao;

	public String activateUserByCode(String verificationCode) {
		EmailVerification emailVerification = emailVerificationDao.findByVerificationCode(verificationCode).get();
		if(emailVerification == null)
			throw new NoSuchElementException("Invalid activation key!");
		emailVerification.setIsExpired(true);
		emailVerification.setIsUsed(true);
		return emailVerification.getEmail();
	}

	public EmailSecurityCode generateSecurityCodeForEmail(EmailForm emailForm) {
		int securityCode = OtpGenerator.generateOtp(6);
		EmailSecurityCode emailSecurityCode = EmailSecurityCode.builder()
				.email(emailForm.getEmail())
				.securityCode(securityCode)
				.date(new Date())
				.isUsed(false)
				.isExpired(false)
				.build();
		return emailSecurityCodeDao.save(emailSecurityCode);
	}

	public EmailVerification addEmailToVerificationList(EmailForm emailForm) {
		String verificationCode = UUID.randomUUID().toString();
		EmailVerification emailVerification = EmailVerification.builder()
				.email(emailForm.getEmail())
				.verificationCode(verificationCode)
				.date(new Date())
				.isUsed(false)
				.isExpired(false)
				.build();
		return emailVerificationDao.save(emailVerification);
	}
}
