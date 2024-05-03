package com.m99.userloginsystem.controller.user;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.m99.userloginsystem.entity.user.User;
import com.m99.userloginsystem.model.user.ProfilePicResponse;
import com.m99.userloginsystem.service.user.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.mail.Multipart;

@RestController
@CrossOrigin
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

	@PostMapping({"/profile-pic"})
	public ResponseEntity<ProfilePicResponse> saveUserProfilePic(@RequestParam("file") MultipartFile file) {
		System.out.println(file.getOriginalFilename());
//		ResponseEntity<Strin>
//		return file.getOriginalFilename();
		ProfilePicResponse response = ProfilePicResponse.builder().response("Received "+ file.getOriginalFilename()).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping({"/user-info/{userIdStr}"})
	public User getUserInfo(@PathVariable String userIdStr) {
		try {
			long userId = Long.parseLong(userIdStr);
			return this.userService.getUserById(userId);
		}catch (Exception e) {
			throw new NoSuchElementException("No user with id "+ userIdStr);
		}
	}
}
