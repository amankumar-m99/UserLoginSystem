package com.m99.userloginsystem.model.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPersonalDetailsModel {
	private long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String phoneNumber;
	private String gender;
	private String country;
	private Date dateOfBirth;
}
