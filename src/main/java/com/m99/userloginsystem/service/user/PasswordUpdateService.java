package com.m99.userloginsystem.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.entity.security.SecurityCode;
import com.m99.userloginsystem.entity.user.User;
import com.m99.userloginsystem.model.SecurityCodePurpose;
import com.m99.userloginsystem.model.user.UpdatePasswordFormModel;
import com.m99.userloginsystem.service.email.EmailSenderService;
import com.m99.userloginsystem.service.security.SecurityCodeService;

@Service
public class PasswordUpdateService {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private SecurityCodeService securityCodeService;

	public boolean updatePassword(UpdatePasswordFormModel model) {
		boolean isSecurityCodeVerified = securityCodeService.verifySecurityCode(model, SecurityCodePurpose.UPDATE_PASSWORD);
		if(!isSecurityCodeVerified) {
			return false;
		}
		boolean isPasswordUpdated = userService.updatePassword(model.getEmail(), model.getNewPassword());
		return isPasswordUpdated;
	}

	public boolean sendSecurityCodeForPasswordUpdate(String emailOrUsername) {
		User user = userService.getUserByUsernameOrEmail(emailOrUsername);
		if(user == null) {
			return false;
		}
		SecurityCode securityCode = securityCodeService.generateSecurityCodeForEmail(user.getEmail(), SecurityCodePurpose.UPDATE_PASSWORD);
		boolean isSent = emailSenderService.sendSecurityCode(securityCode);
		return isSent;
	}
}
