package com.m99.userloginsystem.service.email;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.customexception.email.EmailNotFoundException;
import com.m99.userloginsystem.dao.security.EmailSecurityCodeDao;
import com.m99.userloginsystem.entity.security.EmailSecurityCode;
import com.m99.userloginsystem.model.EmailSecurityCodeForm;
import com.m99.userloginsystem.model.email.EmailForm;
import com.m99.userloginsystem.utils.OtpGenerator;

@Service
public class EmailVerificationService {

	@Autowired
	private EmailSecurityCodeDao emailSecurityCodeDao;

	public String activateUserBySecurityCode(EmailSecurityCodeForm emailSecurityCodeForm) {
		EmailSecurityCode emailSecurityCode = emailSecurityCodeDao.findByEmail(emailSecurityCodeForm.getEmail()).orElse(null);
		if(emailSecurityCode == null)
			throw new NoSuchElementException("Invalid email");
		emailSecurityCode.setIsExpired(true);
		emailSecurityCode.setIsUsed(true);
		return emailSecurityCode.getEmail();
	}

	public EmailSecurityCode generateSecurityCodeForEmail(String email) {
		int securityCode = OtpGenerator.generateOtp(6);
		EmailSecurityCode emailSecurityCode = EmailSecurityCode.builder()
				.email(email)
				.securityCode(securityCode)
				.createdOn(new Date())
				.isUsed(false)
				.isExpired(false)
				.build();
		return emailSecurityCodeDao.save(emailSecurityCode);
	}

	public boolean verifySecurityCode(EmailSecurityCodeForm emailSecurityCodeForm) {
		String email = emailSecurityCodeForm.getEmail();
		int securityCode = emailSecurityCodeForm.getSecurityCode();
		EmailSecurityCode emailSecurityCode = emailSecurityCodeDao.findByEmail(email).orElseThrow(()->new EmailNotFoundException("No email as "+email));
		if(emailSecurityCode.getSecurityCode() != securityCode)
			return false;
		emailSecurityCode.setIsExpired(true);
		emailSecurityCode.setIsUsed(true);
		emailSecurityCodeDao.save(emailSecurityCode);
		return true;
	}
}
