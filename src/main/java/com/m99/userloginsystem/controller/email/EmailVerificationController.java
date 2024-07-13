package com.m99.userloginsystem.controller.email;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.entity.security.EmailSecurityCode;
import com.m99.userloginsystem.model.EmailSecurityCodeForm;
import com.m99.userloginsystem.model.email.EmailForm;
import com.m99.userloginsystem.service.email.EmailSenderService;
import com.m99.userloginsystem.service.email.EmailVerificationService;

@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailVerificationController {

	@Autowired
	private EmailVerificationService emailVerificationService;

	@Autowired
	private EmailSenderService emailSenderService;

	@PostMapping("/security-code")
	public void generateSecurityCodeForEmail(@RequestBody EmailForm emailForm) throws EmailException {
		EmailSecurityCode emailSecurityCode = emailVerificationService.generateSecurityCodeForEmail(emailForm.getEmail());
		String email = emailSecurityCode.getEmail();
		int securityCode = emailSecurityCode.getSecurityCode();
		emailSenderService.sendSecurityCode(email, securityCode);
	}

	@PostMapping("/verify-email")
	public boolean verifyEmailByCode(@RequestBody EmailSecurityCodeForm emailSecurityCodeForm) {
		boolean isVerified = emailVerificationService.verifySecurityCode(emailSecurityCodeForm);
		return isVerified;
	}
}
