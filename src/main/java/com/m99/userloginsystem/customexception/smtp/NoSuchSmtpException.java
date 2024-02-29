package com.m99.userloginsystem.customexception.smtp;

public class NoSuchSmtpException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4848827675432934247L;

	public NoSuchSmtpException(String message) {
		super(message);
	}
}
