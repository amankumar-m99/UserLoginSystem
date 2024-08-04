package com.m99.userloginsystem.utils;

import com.m99.userloginsystem.model.SecurityCodePurpose;
import com.m99.userloginsystem.model.security.ISecurityCodeModel;
import com.m99.userloginsystem.model.security.SecurityCodeModel;

public class SecurityCodeUtils {
	public static SecurityCodeModel getSecurityCodeModelFromInput(ISecurityCodeModel model, SecurityCodePurpose purpose) {
		int securityCodeInt = 0;
		try {
			securityCodeInt = Integer.parseInt(model.getSecurityCode());
		}catch(NumberFormatException exception) {
			return null;
		}
		return new SecurityCodeModel(model.getEmail(), securityCodeInt, purpose);
	}
}
