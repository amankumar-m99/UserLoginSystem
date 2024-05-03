package com.m99.userloginsystem.configuration;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.m99.userloginsystem.configuration.data.StaticData;

import jakarta.annotation.PostConstruct;

@Configuration
public class AppConfig {

	@Value("${application.data.directory.home}")
	private String dataDirectoryHome;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@PostConstruct()
	public void initApplicationDataDirectory() {
		System.out.println("-> Initialising dataDirectory...");
		if(dataDirectoryHome == null || dataDirectoryHome.trim().isEmpty())
			StaticData.setApplicationDataDirectoryParent(System.getProperty("user.dir"));
		else
			StaticData.setApplicationDataDirectoryParent(dataDirectoryHome.replace(".", File.separator));
		System.out.println("-> Initialed dataDirectory to "+ StaticData.getApplicationDataDirectory());
	}

	@Bean
	//used in both but difference in implementation
	//used in code with durgesh
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
}
