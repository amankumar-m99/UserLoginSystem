package com.m99.userloginsystem.initializer;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import com.m99.userloginsystem.entity.user.UserPersonalDetails;
import com.m99.userloginsystem.entity.user.UserSecurityDetails;
import com.m99.userloginsystem.model.user.registration.AccountDetails;
import com.m99.userloginsystem.model.user.registration.UserRegistrationFormModel;
import com.m99.userloginsystem.utils.enums.Gender;

public class StarterDataInitializer {
	public static UserRegistrationFormModel getSuperAdmin() {
		AccountDetails accountDetails = AccountDetails.builder()
				.username("superAdmin")
				.email("superadmin@m99.com")
				.password("1234")
				.roles(Arrays.asList(1).stream().collect(Collectors.toSet()))
				.build();
		UserPersonalDetails personalDetails = UserPersonalDetails.builder()
				.firstName("Super")
				.middleName("")
				.lastName("Admin")
				.phoneNumber("")
				.gender(Gender.MALE)
				.country("")
				.dateOfBirth(new Date())
				.build();
		UserSecurityDetails securityDetails = UserSecurityDetails.builder()
				.recoveryEmail("")
				.recoveryPhoneNumber("")
				.securityQuestion("")
				.securityAnswer("")
				.loginAlert(true)
				.passwordUpdateAlert(true)
				.twoStepLogin(false)
				.build();
		UserRegistrationFormModel registrationFormModel = UserRegistrationFormModel.builder()
				.accountDetails(accountDetails)
				.personalDetails(personalDetails)
				.securityDetails(securityDetails)
				.build();
		return registrationFormModel;
	}

	public static UserRegistrationFormModel getAdmin() {
		AccountDetails accountDetails = AccountDetails.builder()
				.username("admin")
				.email("admin@m99.com")
				.password("1234")
				.roles(Arrays.asList(2).stream().collect(Collectors.toSet()))
				.build();
		UserPersonalDetails personalDetails = UserPersonalDetails.builder()
				.firstName("Admin")
				.middleName("")
				.lastName("")
				.phoneNumber("")
				.gender(Gender.FEMALE)
				.country("")
				.dateOfBirth(new Date())
				.build();
		UserSecurityDetails securityDetails = UserSecurityDetails.builder()
				.recoveryEmail("")
				.recoveryPhoneNumber("")
				.securityQuestion("")
				.securityAnswer("")
				.loginAlert(true)
				.passwordUpdateAlert(true)
				.twoStepLogin(false)
				.build();
		UserRegistrationFormModel registrationFormModel = UserRegistrationFormModel.builder()
				.accountDetails(accountDetails)
				.personalDetails(personalDetails)
				.securityDetails(securityDetails)
				.build();
		return registrationFormModel;
	}

	public static UserRegistrationFormModel getStandardUser() {
		AccountDetails accountDetails = AccountDetails.builder()
				.username("amank")
				.email("amankumar.m99@gmail.com")
				.password("1234")
				.roles(Arrays.asList(3).stream().collect(Collectors.toSet()))
				.build();
		UserPersonalDetails personalDetails = UserPersonalDetails.builder()
				.firstName("Standard")
				.middleName("")
				.lastName("User")
				.phoneNumber("")
				.gender(Gender.OTHER)
				.country("")
				.dateOfBirth(new Date())
				.build();
		UserSecurityDetails securityDetails = UserSecurityDetails.builder()
				.recoveryEmail("")
				.recoveryPhoneNumber("")
				.securityQuestion("")
				.securityAnswer("")
				.loginAlert(true)
				.passwordUpdateAlert(true)
				.twoStepLogin(false)
				.build();
		UserRegistrationFormModel registrationFormModel = UserRegistrationFormModel.builder()
				.accountDetails(accountDetails)
				.personalDetails(personalDetails)
				.securityDetails(securityDetails)
				.build();
		return registrationFormModel;
	}
}
