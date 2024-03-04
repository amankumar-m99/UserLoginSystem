package com.m99.userloginsystem.utils;

import java.util.Random;

public class OtpGenerator {

	public static int generateOtp(int numberOfDigits) {
		StringBuilder sb = new StringBuilder();
        String numbers = "123456789";
        Random random = new Random();
        for (int i=0; i<numberOfDigits; i++) {
        	sb.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return Integer.parseInt(sb.toString());
	}
}
