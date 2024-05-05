package com.m99.userloginsystem.customexception.images;

public class ImageResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2450000636477253837L;

	public ImageResourceNotFoundException() {
		super();
	}

	public ImageResourceNotFoundException(String message) {
		super(message);
	}
}
