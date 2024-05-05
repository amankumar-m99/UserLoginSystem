package com.m99.userloginsystem.controller.images;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.entity.user.profilepic.UserProfilePicResource;
import com.m99.userloginsystem.service.user.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/images")
public class ImageController {

	@Autowired
	private UserService userService;

	@GetMapping(value="/profile-pic/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
	public void getUserProfilePic(@PathVariable("id") String idStr, HttpServletResponse response) throws IOException {
		long id = Long.parseLong(idStr);
		UserProfilePicResource resource = this.userService.getUserProfilePicResource(id);
		response.setContentType(resource.getContentType());
		StreamUtils.copy(resource.getFileInputStream(), response.getOutputStream());
	}
}
