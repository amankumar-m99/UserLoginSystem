package com.m99.userloginsystem.model.user.registration;

import com.m99.userloginsystem.entity.user.UserPersonalDetails;
import com.m99.userloginsystem.entity.user.UserSecurityDetails;

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
public class UserRegistrationFormModel {
	private AccountDetails accountDetails;
	private UserPersonalDetails personalDetails;
	private UserSecurityDetails securityDetails;
}
