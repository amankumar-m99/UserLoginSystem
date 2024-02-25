package com.m99.userloginsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.entity.User;
import com.m99.userloginsystem.service.UserService;

import jakarta.annotation.PostConstruct;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void initRolesAndUsers() {
		System.out.println("->Initialising DB...");
		userService.initRolesAndUsers();
		System.out.println("->DB initialized.");
	}

	@PostMapping({"/register-user"})
	public User registerUser(@RequestBody User user) {
		return userService.registerUser(user);
	}

	@GetMapping({"/admin"})
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "this is admin endpoint";
	}

	@GetMapping({"/user"})
	@PreAuthorize("hasRole('User')")
	public String forUser() {
		return "this is user endpoint";
	}
}
