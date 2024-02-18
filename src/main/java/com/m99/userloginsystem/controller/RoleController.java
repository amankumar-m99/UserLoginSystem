package com.m99.userloginsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.entity.Role;
import com.m99.userloginsystem.service.RoleService;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping({"/createRole"})
	public Role createRole(@RequestBody Role role) {
		return roleService.createNewRole(role);
	}
	public Role getRoles() {
		return null;
	}
}
