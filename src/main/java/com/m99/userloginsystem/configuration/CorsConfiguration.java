package com.m99.userloginsystem.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

	private static final String GET="GET";
	private static final String POST="POST";
	private static final String DELETE="DELETE";
	private static final String PUT="PUT";
	private static final String PATCH="PATCH";

	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
//				WebMvcConfigurer.super.addCorsMappings(registry); // this line was auto generated by IDE
				registry.addMapping("/**")
				.allowedMethods(GET, PUT, PATCH, POST, DELETE)
				.allowedHeaders("*")
				.allowedOriginPatterns("*")
				.allowCredentials(true);
			}
		};
	}
}
