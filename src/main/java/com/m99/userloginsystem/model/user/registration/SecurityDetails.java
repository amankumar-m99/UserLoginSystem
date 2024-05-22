package com.m99.userloginsystem.model.user.registration;

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
public class SecurityDetails {
	private String recoveryEmail;
	private String recoveryPhone;
	private String sequrityQuestion;
	private String sequrityAnswer;
	private Boolean loginAlert;
	private Boolean passwordChangeAlert;
	private Boolean twoStepLogin;
}
