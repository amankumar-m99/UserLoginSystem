package com.m99.userloginsystem.customexception.email;

public class EmailNotVerifiedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8767169730794787632L;

	public EmailNotVerifiedException(String message) {
		super(message);
	}
}
