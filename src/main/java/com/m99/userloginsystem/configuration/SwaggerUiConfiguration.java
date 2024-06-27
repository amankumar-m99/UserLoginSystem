package com.m99.userloginsystem.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerUiConfiguration {

	private static final String[] SWAGGER_URLS = {
	        "/v3/api-docs/**",
	        "/swagger-ui/**",
	        "/v2/api-docs/**",
	        "/swagger-resources/**",
	        "/bus/v3/api-docs/**",
	        "/api-docs/swagger-config/**",
	        "/api-docs/public/**"
	};

	public static String[] getSwaggerUrls() {
		return SWAGGER_URLS;
	}

	@Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}
