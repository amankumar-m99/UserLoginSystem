package com.m99.userloginsystem.utils.enums.enums.enumconverter;

import com.m99.userloginsystem.utils.enums.Gender;

public class GenderEnumConverter {
	public static Gender getGenderFromString(String string) {
		string = string.toUpperCase();
		if(string.equals(Gender.FEMALE.name()))
			return Gender.FEMALE;
		if(string.equals(Gender.MALE.name()))
			return Gender.MALE;
		
		return Gender.OTHER;
	}
}
