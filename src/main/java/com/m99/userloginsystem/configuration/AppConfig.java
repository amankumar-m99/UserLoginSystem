package com.m99.userloginsystem.configuration;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.m99.userloginsystem.configuration.data.ApplicationDataDirectoryPolicy;
import com.m99.userloginsystem.configuration.data.StaticData;
import com.m99.userloginsystem.customexception.datadirectorypolicy.InvalidDataDirectoryPolicyException;
import com.m99.userloginsystem.utils.ConsolePrinter;

import jakarta.annotation.PostConstruct;

@Configuration
public class AppConfig {

	@Value("${application.data.directory.home}")
	private String dataDirectoryHome;

	@Value("${application.data.directory.policy}")
	private String dataDirectoryPolicy;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@PostConstruct()
	public void initApplicationDataDirectory() {
		ConsolePrinter.printInfo("Initialising data-directory...");
		ApplicationDataDirectoryPolicy policy = getApplicationDataDirectoryPolicyFromString(dataDirectoryPolicy);
		if(dataDirectoryHome == null || dataDirectoryHome.trim().isEmpty())
			StaticData.setApplicationDataDirectoryParent(System.getProperty("user.dir"), policy);
		else
			StaticData.setApplicationDataDirectoryParent(dataDirectoryHome.replace(".", File.separator), policy);
		ConsolePrinter.printInfo("Data-directory initialed to "+ StaticData.getApplicationDataDirectory());
	}

	public ApplicationDataDirectoryPolicy getApplicationDataDirectoryPolicyFromString(String policyStr) {
		if(policyStr.toLowerCase().equals("create")) {
			return ApplicationDataDirectoryPolicy.CREATE;
		}
		else if(policyStr.toLowerCase().equals("update")) {
			return ApplicationDataDirectoryPolicy.UPDATE;
		}
		throw new InvalidDataDirectoryPolicyException("In application.properties, '"+policyStr+"' is not a valid policy");
	}

	@Bean
	//used in both but difference in implementation
	//used in code with durgesh
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
}
