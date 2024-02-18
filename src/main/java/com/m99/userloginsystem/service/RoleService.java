package com.m99.userloginsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.dao.RoleDao;
import com.m99.userloginsystem.entity.Role;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	public Role createNewRole(Role role) {
		return roleDao.save(role);
	}
}
