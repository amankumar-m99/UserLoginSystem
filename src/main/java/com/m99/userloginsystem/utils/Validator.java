package com.m99.userloginsystem.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public static boolean isValidUsername(String username) {
		Pattern REGEX = Pattern.compile("^[A-Za-z]\\w{5, 29}$");
		Matcher matcher = REGEX.matcher(username);
		return matcher.matches();
	}
	public static boolean isValidEmail(String email) {
		Pattern REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = REGEX.matcher(email);
		return matcher.matches();
	}
}
