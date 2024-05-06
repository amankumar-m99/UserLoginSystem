package com.m99.userloginsystem.customexception.datadirectorypolicy;

public class InvalidDataDirectoryPolicyException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6000639539381414680L;

	public InvalidDataDirectoryPolicyException() {
		super();
	}

	public InvalidDataDirectoryPolicyException(String message) {
		super(message);
	}
}
