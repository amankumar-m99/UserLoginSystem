package com.m99.userloginsystem.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping({"/all-users"})
	@PreAuthorize("hasRole('admin')")
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}

	@GetMapping({"/current-user"})
	public String getCurrentUser(Principal principal) {
		return principal.getName();
	}
}
