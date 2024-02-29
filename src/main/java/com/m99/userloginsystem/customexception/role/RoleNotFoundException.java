package com.m99.userloginsystem.customexception.role;

public class RoleNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6886361037412714075L;

	public RoleNotFoundException(String message) {
		super(message);
	}
}
