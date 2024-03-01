package com.m99.userloginsystem.service;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.dao.AccountActivationDao;
import com.m99.userloginsystem.entity.AccountActivation;
import com.m99.userloginsystem.model.EmailVerification;

@Service
public class AccountActivationService {

	@Autowired
	private AccountActivationDao accountActivationDao;

	public String activateAccountByKey(String key) {
		AccountActivation accountActivation = accountActivationDao.findByActivationKey(key).get();
		if(accountActivation == null)
			throw new NoSuchElementException("Invalid activation key!");
		return accountActivation.getEmail();
	}

	public String verifyEmail(EmailVerification emailVerification) {
		String key = UUID.randomUUID().toString();
		AccountActivation accountActivation = AccountActivation.builder()
				.email(emailVerification.getEmail())
				.activationKey(key)
				.build();
		accountActivationDao.save(accountActivation);
		return key;
	}
}
