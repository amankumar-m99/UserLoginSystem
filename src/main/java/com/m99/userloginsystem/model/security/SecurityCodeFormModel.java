package com.m99.userloginsystem.model.security;

import com.m99.userloginsystem.model.SecurityCodePurpose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityCodeFormModel {
	private String email;
	private int securityCode;
	private SecurityCodePurpose purpose;
}
