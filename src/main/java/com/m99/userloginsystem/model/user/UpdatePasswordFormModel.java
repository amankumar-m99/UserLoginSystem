package com.m99.userloginsystem.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordFormModel {
	private String username;
	private String newPassword;
	private String securityCode;
}
