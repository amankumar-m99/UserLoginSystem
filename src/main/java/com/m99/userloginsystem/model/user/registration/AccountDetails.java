package com.m99.userloginsystem.model.user.registration;

import java.util.Set;

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
public class AccountDetails {
	private String username;
	private String email;
	private String password;
	private Boolean promotionalMails;
	private Set<Integer> roles;
}
