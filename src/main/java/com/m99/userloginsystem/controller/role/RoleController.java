package com.m99.userloginsystem.controller.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.entity.role.Role;
import com.m99.userloginsystem.service.role.RoleService;

@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping({"/create"})
	@PreAuthorize("hasRole('admin')")
	public Role createRole(@RequestBody Role role) {
		return roleService.createNewRole(role);
	}

	@GetMapping({"/all-roles"})
	@PreAuthorize("hasRole('admin')")
	public List<Role> getRoles() {
		return roleService.getAllRoles();
	}
}
