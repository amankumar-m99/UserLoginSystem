package com.m99.userloginsystem.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.dao.RoleDao;
import com.m99.userloginsystem.dao.UserDao;
import com.m99.userloginsystem.entity.Role;
import com.m99.userloginsystem.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	public User registerUser(User user) {
		return userDao.save(user);
	}

	public void initRolesAndUsers() {
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("This role handles admin tasks");
		roleDao.save(adminRole);

		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("This normal default user");
		roleDao.save(userRole);

		Set<Role> setA = new HashSet<>();
		setA.add(adminRole);
		User userA = new User();
		userA.setUsername("admin");
		userA.setEmail("admin@gmail.com");
		userA.setPassword("1234A");
		userA.setRole(setA);
		userDao.save(userA);

		Set<Role> setU = new HashSet<>();
		setU.add(userRole);
		User userU = new User();
		userU.setUsername("amankumar");
		userU.setEmail("amankumar@gmail.com");
		userU.setPassword("1234U");
		userU.setRole(setU);
		userDao.save(userU);
	}
}
