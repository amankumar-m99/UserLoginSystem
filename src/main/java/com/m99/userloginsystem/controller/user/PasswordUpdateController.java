package com.m99.userloginsystem.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.model.email.EmailForm;
import com.m99.userloginsystem.model.user.UpdatePasswordModel;
import com.m99.userloginsystem.service.user.PasswordUpdateService;

@RestController
@CrossOrigin
@RequestMapping("/password-update")
public class PasswordUpdateController {

	@Autowired
	PasswordUpdateService passwordUpdateService;

	@PostMapping({"/update"})
	public Boolean updatePassword(@RequestBody UpdatePasswordModel updatePasswordModel) throws Exception {
		return passwordUpdateService.updatePassword(updatePasswordModel);
	}

	@PostMapping({"/send-security-code"})
	public Boolean sendSecurityCode(@RequestBody EmailForm emailForm) throws Exception {
		return passwordUpdateService.sendSecurityCodeForPasswordUpdate(emailForm.getEmail());
	}
}
