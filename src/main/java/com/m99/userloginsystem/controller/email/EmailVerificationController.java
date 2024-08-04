package com.m99.userloginsystem.controller.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.entity.security.SecurityCode;
import com.m99.userloginsystem.model.SecurityCodePurpose;
import com.m99.userloginsystem.model.email.EmailFormModel;
import com.m99.userloginsystem.model.security.SecurityCodeFormModel;
import com.m99.userloginsystem.service.email.EmailSenderService;
import com.m99.userloginsystem.service.security.SecurityCodeService;

@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailVerificationController {

	@Autowired
	private SecurityCodeService securityCodeService;

	@Autowired
	private EmailSenderService emailSenderService;

	@PostMapping("/security-code")
	public void generateSecurityCodeForEmail(@RequestBody EmailFormModel emailFormModel) {
		SecurityCode emailSecurityCode = securityCodeService.generateSecurityCodeForEmail(emailFormModel.getEmail(), SecurityCodePurpose.REGISTRATION);
		emailSenderService.sendSecurityCode(emailSecurityCode);
	}

	@PostMapping("/verify-email")
	public boolean verifyEmailByCode(@RequestBody SecurityCodeFormModel securityCodeFormModel) {
		boolean isVerified = securityCodeService.verifySecurityCode(securityCodeFormModel);
		return isVerified;
	}
}
