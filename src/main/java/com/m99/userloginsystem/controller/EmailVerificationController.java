package com.m99.userloginsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.entity.EmailVerification;
import com.m99.userloginsystem.model.EmailVerificationForm;
import com.m99.userloginsystem.service.email.EmailSenderService;
import com.m99.userloginsystem.service.email.EmailVerificationService;

@RestController
@CrossOrigin
public class EmailVerificationController {

	@Autowired
	private EmailVerificationService emailVerificationService;

	@Autowired
	private EmailSenderService mailSenderService;

	@PostMapping("/verify-email")
	public void verifyEmailByCode(@RequestBody EmailVerificationForm emailVerificationForm) {
		EmailVerification emailToVerification = emailVerificationService.addEmailToVerificationList(emailVerificationForm);
		String email = emailToVerification.getEmail();
		String verificationCode = emailToVerification.getVerificationCode();
		mailSenderService.sendEmailVerificationLink(email, verificationCode);
	}
}
