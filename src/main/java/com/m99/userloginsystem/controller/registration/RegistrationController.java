package com.m99.userloginsystem.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.entity.user.User;
import com.m99.userloginsystem.model.user.registration.UserRegistrationFormModel;
import com.m99.userloginsystem.service.registration.RegistrationService;
import com.m99.userloginsystem.service.user.UserService;

@RestController
@CrossOrigin
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	private RegistrationService registrationService;

	@PostMapping({"/user"})
	public User registerUser(@RequestBody UserRegistrationFormModel userRegistrationFormModel) {
		return userService.registerUser(userRegistrationFormModel);
	}

	@GetMapping("/username-available/{username}")
	public boolean isUsernameAvailable(@PathVariable String username) {
		return registrationService.isUsernameAvailable(username);
	}

	@GetMapping("/email-in-use/{email}")
	public boolean isEmailInUse(@PathVariable String email) {
		return registrationService.isEmailInUse(email);
	}

	@GetMapping("/has-email-been-in-use/{email}")
	public boolean hasEmailBeenInUse(@PathVariable String email) {
		return registrationService.hasEmailBeenInUse(email);
	}
}
