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
public class PersonalDetails {
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String country;
	private String dateOfBirth;
}
