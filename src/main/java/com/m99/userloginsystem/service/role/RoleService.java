package com.m99.userloginsystem.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.dao.role.RoleDao;
import com.m99.userloginsystem.entity.role.Role;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	public Role createNewRole(Role role) {
		return roleDao.save(role);
	}

	public List<Role> getAllRoles() {
		return roleDao.findAll();
	}
}
