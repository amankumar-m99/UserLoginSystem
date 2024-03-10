package com.m99.userloginsystem.customexception.email;

public class EmailNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7178561179220165744L;

	public EmailNotFoundException(String message) {
		super(message);
	}
}
