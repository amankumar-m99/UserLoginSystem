package com.m99.userloginsystem.service.user;

import org.springframework.stereotype.Service;

import com.m99.userloginsystem.entity.user.UserPersonalDetails;
import com.m99.userloginsystem.model.user.UserPersonalDetailsModel;
import com.m99.userloginsystem.utils.enums.enums.enumconverter.GenderEnumConverter;

@Service
public class UserPersonalDetailsService {
	public static void updateDetails(UserPersonalDetails details, UserPersonalDetailsModel model) {
		details.setFirstName(model.getFirstName());
		details.setMiddleName(model.getMiddleName());
		details.setLastName(model.getLastName());
		details.setPhoneNumber(model.getPhoneNumber());
		details.setGender(GenderEnumConverter.getGenderFromString(model.getGender()));
		details.setCountry(model.getCountry());
		details.setDateOfBirth(model.getDateOfBirth());
	}
}
