package com.m99.userloginsystem.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSecurityDetailsModel {
	private long id;
	private String recoveryEmail;
	private String recoveryPhoneNumber;
	private String securityQuestion;
	private String securityAnswer;
	private Boolean loginAlert;
	private Boolean passwordUpdateAlert;
	private Boolean twoStepLogin;
}
