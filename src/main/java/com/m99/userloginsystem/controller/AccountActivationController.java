package com.m99.userloginsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.m99.userloginsystem.service.AccountActivationService;

public class AccountActivationController {

	@Autowired
	private AccountActivationService accountActivationService;

	@GetMapping("/activate/{id}")
	public String activateAccountByKey(@RequestParam String key) {
		return accountActivationService.activateAccountByKey(key);
	}
}
