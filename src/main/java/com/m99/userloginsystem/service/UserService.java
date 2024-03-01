package com.m99.userloginsystem.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.customexception.user.EmailAlreadyExistsException;
import com.m99.userloginsystem.customexception.user.UserNameNotAvailableException;
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

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> getAllUsers(){
		return userDao.findAll();
	}

	public User registerUser(User user) {
		if(!isUserAvailable(user.getUsername(), UserLookupType.USERNAME)) {
			throw new UserNameNotAvailableException("username already exists!");
		}
		if(!isUserAvailable(user.getEmail(), UserLookupType.EMAIL)) {
			throw new EmailAlreadyExistsException("email already exists!");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(user.getRoles() == null || user.getRoles().size() == 0) {
			Role role = roleDao.findByRoleName("user").get();
			if(role!=null) {
				Set<Role> roles = new HashSet<Role>();
				roles.add(role);
				user.setRoles(roles);
			}
		}
		return userDao.save(user);
	}

	private boolean isUserAvailable(String key, UserLookupType lookupType) {
		User user = null;
		try {
			switch (lookupType) {
			case EMAIL:
				user = userDao.findByEmail(key).get();
				break;
			case USERNAME:
				user = userDao.findByUsername(key).get();
				break;
			}
		} catch(NoSuchElementException e) {
			return true;
		}
		if(user != null)
			return false;
		return true;
	}

	public void initRolesAndUsers() {
		Role adminRole = new Role();
		adminRole.setRoleName("admin");
		adminRole.setRoleDescription("This role handles admin tasks");
		roleDao.save(adminRole);

		Role userRole = new Role();
		userRole.setRoleName("user");
		userRole.setRoleDescription("This normal default user");
		roleDao.save(userRole);

		Set<Role> setA = new HashSet<>();
		setA.add(adminRole);
//		User userA = new User();
//		userA.setUsername("admin");
//		userA.setEmail("admin@gmail.com");
//		userA.setPassword(passwordEncoder.encode("1234A"));
//		userA.setRoles(setA);
		User userA = User.builder()
				.username("admin")
				.email("admin@gmail.com")
				.password("1234A")
				.roles(setA)
				.isLocked(false)
				.isEnabled(false)
				.isAccountExpired(false)
				.isCredentialExpired(false)
				.build();
		userDao.save(userA);

		Set<Role> setU = new HashSet<>();
		setU.add(userRole);
//		User userU = new User();
//		userU.setUsername("amankumar");
//		userU.setEmail("amankumar@gmail.com");
//		userU.setPassword(passwordEncoder.encode("1234U"));
//		userU.setRoles(setU);
		User userU = User.builder()
				.username("aman")
				.email("amankumar.m99@gmail.com")
				.password("1234U")
				.roles(setU)
				.isLocked(false)
				.isEnabled(false)
				.isAccountExpired(false)
				.isCredentialExpired(false)
				.build();
		userDao.save(userU);
	}
}
