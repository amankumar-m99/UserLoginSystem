package com.m99.userloginsystem.model.security;

import com.m99.userloginsystem.model.SecurityCodePurpose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SecurityCodeModel{
	private String email;
	private int securityCode;
	private SecurityCodePurpose purpose;
}
