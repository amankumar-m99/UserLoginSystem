package com.m99.userloginsystem.model.email;

import lombok.Getter;

@Getter
public class EmailModel {

	private String prefix;
	private String host;

	public EmailModel(String emailId) {
		String[] arr = emailId.split("@");
		this.prefix = arr[0];
		this.host = arr[1];
	}

	public EmailModel(String prefix, String host) {
		super();
		this.prefix = prefix;
		this.host = host;
	}

	public String getEmailId() {
		return prefix+"@"+host;
	}

	
}
