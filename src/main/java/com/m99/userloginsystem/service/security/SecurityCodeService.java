package com.m99.userloginsystem.service.security;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.customexception.email.EmailNotFoundException;
import com.m99.userloginsystem.dao.security.SecurityCodeDao;
import com.m99.userloginsystem.entity.security.SecurityCode;
import com.m99.userloginsystem.model.SecurityCodePurpose;
import com.m99.userloginsystem.model.security.SecurityCodeFormModel;
import com.m99.userloginsystem.utils.OtpGenerator;

@Service
public class SecurityCodeService {

	@Autowired
	private SecurityCodeDao securityCodeDao;

	public String activateUserBySecurityCode(SecurityCodeFormModel securityCodeFormModel) {
		SecurityCode securityCode = securityCodeDao.findByEmail(securityCodeFormModel.getEmail()).orElse(null);
		if(securityCode == null)
			throw new NoSuchElementException("Invalid email");
		securityCode.setIsExpired(true);
		securityCode.setIsUsed(true);
		return securityCode.getEmail();
	}

	public SecurityCode generateSecurityCodeForEmail(String email, SecurityCodePurpose securityCodePurpose) {
		SecurityCode securityCode = SecurityCode.builder()
				.email(email)
				.securityCode(OtpGenerator.generateOtp(6))
				.purpose(securityCodePurpose)
				.createdOn(new Date())
				.isUsed(false)
				.isExpired(false)
				.build();
		return securityCodeDao.save(securityCode);
	}

	public boolean verifySecurityCode(SecurityCodeFormModel securityCodeFormModel) {
		String email = securityCodeFormModel.getEmail();
		int securityCode = securityCodeFormModel.getSecurityCode();
		SecurityCode emailSecurityCode = securityCodeDao.findByEmail(email).orElseThrow(()->new EmailNotFoundException("No email as "+email));
		if(emailSecurityCode.getSecurityCode() != securityCode)
			return false;
		emailSecurityCode.setIsExpired(true);
		emailSecurityCode.setIsUsed(true);
		securityCodeDao.save(emailSecurityCode);
		return true;
	}
}
