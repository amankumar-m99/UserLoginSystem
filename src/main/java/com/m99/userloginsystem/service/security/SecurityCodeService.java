package com.m99.userloginsystem.service.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.customexception.email.EmailNotFoundException;
import com.m99.userloginsystem.dao.security.SecurityCodeDao;
import com.m99.userloginsystem.entity.security.SecurityCode;
import com.m99.userloginsystem.model.SecurityCodePurpose;
import com.m99.userloginsystem.model.security.ISecurityCodeModel;
import com.m99.userloginsystem.model.security.SecurityCodeModel;
import com.m99.userloginsystem.utils.OtpGenerator;
import com.m99.userloginsystem.utils.SecurityCodeUtils;

@Service
public class SecurityCodeService {

	@Autowired
	private SecurityCodeDao securityCodeDao;

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

	public boolean verifySecurityCode(ISecurityCodeModel model, SecurityCodePurpose purpose) {
		SecurityCodeModel securityCodeModel = SecurityCodeUtils.getSecurityCodeModelFromInput(model, purpose);
		String email = securityCodeModel.getEmail();
		int providedSecurityCode = securityCodeModel.getSecurityCode();
		SecurityCode securityCode = securityCodeDao.findByEmail(email).orElseThrow(()->new EmailNotFoundException("No email as "+email));
		if(securityCode == null) {
			return false;
		}
		if(securityCode.getIsExpired() || securityCode.getIsUsed()) {
			return false;
		}
		if(securityCode.getSecurityCode() != providedSecurityCode) {
			return false;
		}
		if(!securityCode.getPurpose().equals(securityCodeModel.getPurpose())) {
			return false;
		}
		securityCode.setIsUsed(true);
		securityCode.setIsExpired(true);
		securityCodeDao.save(securityCode);
		return true;
	}
}
