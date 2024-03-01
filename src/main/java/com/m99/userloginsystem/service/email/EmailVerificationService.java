package com.m99.userloginsystem.service.email;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.dao.EmailVerificationDao;
import com.m99.userloginsystem.entity.EmailVerification;
import com.m99.userloginsystem.model.EmailVerificationForm;

@Service
public class EmailVerificationService {

	@Autowired
	private EmailVerificationDao accountActivationDao;

	public String activateUserByCode(String verificationCode) {
		EmailVerification emailVerification = accountActivationDao.findByVerificationCode(verificationCode).get();
		if(emailVerification == null)
			throw new NoSuchElementException("Invalid activation key!");
		return emailVerification.getEmail();
	}

	public EmailVerification addEmailToVerificationList(EmailVerificationForm emailVerificationForm) {
		String verificationCode = UUID.randomUUID().toString();
		EmailVerification emailVerification = EmailVerification.builder()
				.email(emailVerificationForm.getEmail())
				.verificationCode(verificationCode)
				.date(new Date())
				.isUsed(false)
				.isExpired(false)
				.build();
		return accountActivationDao.save(emailVerification);
	}
}
