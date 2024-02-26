package com.m99.userloginsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.entity.Role;
import com.m99.userloginsystem.service.RoleService;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping({"/create-role"})
	public Role createRole(@RequestBody Role role) {
		return roleService.createNewRole(role);
	}

	@GetMapping({"/get-all-roles"})
	public List<Role> getRoles() {
		return roleService.getAllRoles();
	}
}
