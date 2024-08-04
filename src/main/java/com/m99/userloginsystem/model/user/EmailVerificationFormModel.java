package com.m99.userloginsystem.model.user;

import com.m99.userloginsystem.model.security.ISecurityCodeModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerificationFormModel implements ISecurityCodeModel{
	private String email;
	private String securityCode;
}
