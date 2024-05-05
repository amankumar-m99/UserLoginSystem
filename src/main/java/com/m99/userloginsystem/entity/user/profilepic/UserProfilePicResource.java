package com.m99.userloginsystem.entity.user.profilepic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.http.MediaType;

public class UserProfilePicResource {
	private File file;

	public UserProfilePicResource(File file) {
		this.file=file;
	}

	public FileInputStream getFileInputStream() {
		try {
			return new FileInputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getContentType() {
		String fileName = file.getName().toLowerCase(); 
		if(fileName.endsWith(".png"))
			return MediaType.IMAGE_PNG_VALUE;
		else if(fileName.endsWith(".jpg"))
			return MediaType.IMAGE_JPEG_VALUE;
		else if(fileName.endsWith(".jpeg"))
			return MediaType.IMAGE_JPEG_VALUE;
		return MediaType.IMAGE_PNG_VALUE;
	}
}
