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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.m99.userloginsystem.entity.user.User;
import com.m99.userloginsystem.entity.user.UserPersonalDetails;
import com.m99.userloginsystem.entity.user.UserSecurityDetails;
import com.m99.userloginsystem.model.user.ProfilePicResponse;
import com.m99.userloginsystem.model.user.UserPersonalDetailsModel;
import com.m99.userloginsystem.model.user.UserSecurityDetailsModel;
import com.m99.userloginsystem.service.user.UserService;
import com.m99.userloginsystem.utils.ConsolePrinter;
import com.m99.userloginsystem.utils.UserExracter;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void initRolesAndUsers() {
		ConsolePrinter.printInfo("Initialising database...");
		userService.initRolesAndUsers();
		ConsolePrinter.printInfo("Database initialized.");
	}

	@GetMapping({"/all"})
	@PreAuthorize("hasRole('admin')")
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}

	@GetMapping({"/current"})
	public String getCurrentUser(Principal principal) {
		return principal.getName();
	}

	@PostMapping({"/profile-pic"})
	@CrossOrigin
	public ResponseEntity<ProfilePicResponse> saveUserProfilePic(HttpServletRequest request, @RequestParam("profile-pic") MultipartFile multipartFile) {
		ResponseEntity<ProfilePicResponse> response;
//		String type = multipartFile.getContentType().toLowerCase();
		String type = multipartFile.getOriginalFilename().toLowerCase();
		if(!(type.endsWith("jpeg") || type.endsWith("jpg") || type.endsWith("png"))) {
			response = new ResponseEntity<>(ProfilePicResponse.builder().response("Only jpeg and png files are accepted.").build(), HttpStatus.CONFLICT);
			return response;
		}
		long id = UserExracter.getUserIdFromRequest(request);
		if(id == -1) {
			response = new ResponseEntity<>(ProfilePicResponse.builder().response("Couldn't identify user.").build(), HttpStatus.CONFLICT);
			return response;
		}

		String result = this.userService.saveUserProfilePic(id, multipartFile);
		if(result != null && !result.trim().isEmpty())
			response = new ResponseEntity<>(ProfilePicResponse.builder().response(result).build(), HttpStatus.OK);
		else
			response = new ResponseEntity<>(ProfilePicResponse.builder().response("Error while saving file at our end.").build(), HttpStatus.CONFLICT);
		return response;
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

	@PostMapping({"/update-personal-details"})
	@CrossOrigin
	public UserPersonalDetails updateUserPersonalDetails(@RequestBody UserPersonalDetailsModel model) {
		return userService.updateUserPersonalDetails(model);
	}

	@PostMapping({"/update-security-details"})
	@CrossOrigin
	public UserSecurityDetails updateUserSecurityDetails(@RequestBody UserSecurityDetailsModel model) {
		return userService.updateUserSecurityDetails(model);
	}
}
