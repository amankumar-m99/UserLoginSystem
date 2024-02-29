package com.m99.userloginsystem.customexception.user;

public class UserNameNotAvailableException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 808606709654942614L;

	public UserNameNotAvailableException(String message) {
		super(message);
	}
}
