package com.m99.userloginsystem.service.user;

import org.springframework.stereotype.Service;

import com.m99.userloginsystem.entity.user.UserSecurityDetails;
import com.m99.userloginsystem.model.user.UserSecurityDetailsModel;

@Service
public class UserSecurityDetailsService {
	public static void updateDetails(UserSecurityDetails details, UserSecurityDetailsModel model) {
		details.setRecoveryEmail(model.getRecoveryEmail());
		details.setRecoveryPhoneNumber(model.getRecoveryPhoneNumber());
		details.setSecurityQuestion(model.getSecurityQuestion());
		details.setSecurityAnswer(model.getSecurityAnswer());
		details.setLoginAlert(model.getLoginAlert());
		details.setPasswordUpdateAlert(model.getPasswordUpdateAlert());
		details.setTwoStepLogin(model.getTwoStepLogin());
	}
}
