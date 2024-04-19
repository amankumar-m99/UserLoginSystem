package com.m99.userloginsystem.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSecurityDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String recoveryEmail;
	private String recoveryPhoneNumber;
	private String securityQuestion;
	private String securityAnswer;
	private Boolean loginAlert;
	private Boolean passwordUpdateAlert;
	private Boolean twoStepLogin;
}
