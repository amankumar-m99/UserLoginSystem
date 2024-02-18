package com.m99.userloginsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
		userService.initRolesAndUsers();
	}

	@PostMapping({"/registerUser"})
	public User registerUser(@RequestBody User user) {
		return userService.registerUser(user);
	}

	@GetMapping({"/admin"})
	public String forAdmin() {
		return "this is admin endpoint";
	}

	@GetMapping({"/user"})
	public String forUser() {
		return "this is user endpoint";
	}
}
