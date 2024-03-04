package com.m99.userloginsystem.controller;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.entity.EmailSecurityCode;
import com.m99.userloginsystem.model.EmailForm;
import com.m99.userloginsystem.service.email.EmailSenderService;
import com.m99.userloginsystem.service.email.EmailVerificationService;

@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailVerificationController {

	@Autowired
	private EmailVerificationService emailVerificationService;

	@Autowired
	private EmailSenderService mailSenderService;

	@PostMapping("/security-code")
	public void generateSecurityCodeForEmail(@RequestBody EmailForm emailForm) throws EmailException {
		EmailSecurityCode emailSecurityCode = emailVerificationService.generateSecurityCodeForEmail(emailForm);
		String email = emailSecurityCode.getEmail();
		int securityCode = emailSecurityCode.getSecurityCode();
		mailSenderService.sendSecurityCode(email, securityCode);
	}
//	@PostMapping("/verify-email")
//	public void verifyEmailByCode(@RequestBody EmailForm emailForm) {
//		EmailVerification emailToVerification = emailVerificationService.addEmailToVerificationList(EmailForm);
//		String email = emailToVerification.getEmail();
//		String verificationCode = emailToVerification.getVerificationCode();
//		mailSenderService.sendEmailVerificationLink(email, verificationCode);
//	}
}
