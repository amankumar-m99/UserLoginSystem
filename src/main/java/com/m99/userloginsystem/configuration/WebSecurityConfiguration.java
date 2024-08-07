package com.m99.userloginsystem.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.m99.userloginsystem.configuration.swagger.SwaggerUiConfiguration;
import com.m99.userloginsystem.security.jwt.JwtAuthenticationEntryPoint;
import com.m99.userloginsystem.security.jwt.JwtAuthenticationFilter;
import com.m99.userloginsystem.service.jwt.JwtService;

@Configuration
@EnableWebMvc //for custom errors
@EnableWebSecurity //for role based auth
//@EnableGlobalMethodSecurity(prePostEnabled = true) //for role based auth but its deprecated
@EnableMethodSecurity // by default (prePostEnabled = true)
public class WebSecurityConfiguration {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
//	private UserDetailsService userDetailsService; //cw durgesh
	private JwtService userDetailsService; //learn programming yourself

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests()
                .requestMatchers(SwaggerUiConfiguration.getSwaggerUrls()).permitAll()
                .requestMatchers("/image/**").permitAll()
                .requestMatchers("/jwt/login").permitAll()
                .requestMatchers("/jwt/activate").permitAll()
                .requestMatchers("/register/**").permitAll()
                .requestMatchers("/password-update/update").permitAll()
                .requestMatchers("/password-update/send-security-code").permitAll()
                .requestMatchers("/enable").permitAll()
                .requestMatchers("/email/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		httpSecurity.cors(); // to accept cross origin auth requests
        return httpSecurity.build();
    }

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.csrf(AbstractHttpConfigurer::disable)
//	      .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//	              authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//	              sample URLs
//	                      .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//	                      .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//	                      .requestMatchers("/login/**").permitAll()
//	                      .requestMatchers("/images/**").permitAll()
//	                      .requestMatchers("/jwt/login").permitAll()
//	                      .requestMatchers("/jwt/activate").permitAll()
//	                      .requestMatchers("/register/**").permitAll()
//	                      .requestMatchers("/password-update/user").permitAll()
//	                      .requestMatchers("/enable").permitAll()
//	                      .requestMatchers("/email/**").permitAll()
//	                      .anyRequest().authenticated())
//	      .httpBasic(Customizer.withDefaults())
//	      .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
//	      .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//		httpSecurity.cors(corsConfigurer->{
//			corsConfigurer.configurationSource(getCorsConfigurationSource());
//		});
//	    return httpSecurity.build();
//	}

//	@Bean
//	CorsConfigurationSource getCorsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("*"));
//		configuration.setAllowedMethods(Arrays.asList("*"));
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", configuration);
//	    return source;
//	}

	@Bean
	//used in code with durgesh
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return daoAuthenticationProvider;
	}

	@Autowired
	//used in learn programming yourself
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

}
