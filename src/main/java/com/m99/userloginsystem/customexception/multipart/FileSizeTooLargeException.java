package com.m99.userloginsystem.customexception.multipart;

public class FileSizeTooLargeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9148274187344380007L;

	public FileSizeTooLargeException() {
		super();
	}

	public FileSizeTooLargeException(String message) {
		super(message);
	}
}
