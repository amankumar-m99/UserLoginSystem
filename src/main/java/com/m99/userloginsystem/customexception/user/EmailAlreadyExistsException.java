package com.m99.userloginsystem.customexception.user;

public class EmailAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8107465489028135387L;

	public EmailAlreadyExistsException(String message) {
		super(message);
	}
}
