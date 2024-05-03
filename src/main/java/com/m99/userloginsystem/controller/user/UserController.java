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
	public ResponseEntity<ProfilePicResponse> saveUserProfilePic(@RequestParam("file") MultipartFile multipartFile) {
		String baseUrl = "profile-pic/";
		String result = this.userService.saveUserProfilePic(multipartFile);
		ResponseEntity<ProfilePicResponse> response;
		if(result != null && !result.trim().isEmpty())
			response = new ResponseEntity<>(ProfilePicResponse.builder().response(baseUrl+result).build(), HttpStatus.OK);
		else
			response = new ResponseEntity<>(ProfilePicResponse.builder().response("").build(), HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}

	@GetMapping({"/profile-pic/{imageName}"})
	public ResponseEntity<byte[]> getUserProfilePic(@PathVariable String imageName) {
		byte[] data = this.userService.getUserProfilePic(imageName);
		HttpStatus status;
//		if(file != null)
//			status = HttpStatus.OK;
//		else
//			status = HttpStatus.NO_CONTENT;
		return ResponseEntity.ok().body(data);
//		return new ResponseEntity<>(file, status);
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
