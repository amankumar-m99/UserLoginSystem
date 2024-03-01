package com.m99.userloginsystem.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.customexception.user.EmailAlreadyExistsException;
import com.m99.userloginsystem.customexception.user.UserNameNotAvailableException;
import com.m99.userloginsystem.dao.RoleDao;
import com.m99.userloginsystem.dao.UserDao;
import com.m99.userloginsystem.entity.Role;
import com.m99.userloginsystem.entity.User;
import com.m99.userloginsystem.model.UserForm;

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

	public User registerUser(UserForm userForm) {
		if(!isUserAvailable(userForm.getUsername(), UserLookupType.USERNAME)) {
			throw new UserNameNotAvailableException("username already exists!");
		}
		if(!isUserAvailable(userForm.getEmail(), UserLookupType.EMAIL)) {
			throw new EmailAlreadyExistsException("email already exists!");
		}
		User user = createUserFromUserForm(userForm);
		return userDao.save(user);
	}

	private User createUserFromUserForm(UserForm userForm) {
		User user = User.builder()
				.username(userForm.getUsername())
				.email(userForm.getEmail())
				.password(passwordEncoder.encode(userForm.getPassword()))
				.roles(getRolesFromIds(userForm.getRoles()))
				.isLocked(false)
				.isEnabled(true)
				.isAccountExpired(false)
				.isCredentialExpired(false)
				.build();
		if(user.getRoles() == null || user.getRoles().size() == 0) {
			Role role = roleDao.findByRoleName("user").get();
			if(role!=null) {
				Set<Role> roles = new HashSet<Role>();
				roles.add(role);
				user.setRoles(roles);
			}
		}
		return user;
	}

	private Set<Role> getRolesFromIds(Set<Integer> roleIds){
		Set<Role> roles = new HashSet<>();
		roleIds.forEach(i->{
			roles.add(roleDao.findById(i).get());
		});
		return roles;
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
		initRoles();
		initUsers();
	}

	private void initRoles() {
		Role superAdminRole = Role.builder().roleName("superadmin").roleDescription("The supreme admin").build();
		roleDao.save(superAdminRole);
		Role adminRole = Role.builder().roleName("admin").roleDescription("The admin").build();
		roleDao.save(adminRole);
		Role userRole = Role.builder().roleName("user").roleDescription("The default user").build();
		roleDao.save(userRole);
	}

	private void initUsers(){
		UserForm superAdminUserForm = UserForm.builder()
				.username("superAdmin")
				.email("superadmin@m99.com")
				.password("1234S")
				.roles(Arrays.asList(1).stream().collect(Collectors.toSet()))
				.build();
		userDao.save(createUserFromUserForm(superAdminUserForm));

		UserForm adminUserForm = UserForm.builder()
				.username("admin")
				.email("admin@m99.com")
				.password("1234A")
				.roles(Arrays.asList(2).stream().collect(Collectors.toSet()))
				.build();
		userDao.save(createUserFromUserForm(adminUserForm));

		UserForm userForm = UserForm.builder()
				.username("amank")
				.email("amankumar@m99.com")
				.password("1234U")
				.roles(Arrays.asList(2).stream().collect(Collectors.toSet()))
				.build();
		userDao.save(createUserFromUserForm(userForm));
	}
}
