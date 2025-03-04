package com.logistics.moolryu.global.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.logistics.moolryu.global.security.filter.JwtAuthorizationFilter;
import com.logistics.moolryu.global.security.user.UserDetailsServiceImpl;
import com.logistics.moolryu.global.security.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtUtil jwtUtil;
	private final UserDetailsServiceImpl userDetailsService;

	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter() {
		return new JwtAuthorizationFilter(jwtUtil, userDetailsService);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());

		http.sessionManagement(session ->
			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.authorizeHttpRequests(auth ->
			auth
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers("/api/users/signup", "/api/users/login").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
				.anyRequest().authenticated()
		);

		http.formLogin(form -> form.disable());

		http.addFilterBefore(
			new JwtAuthorizationFilter(jwtUtil, userDetailsService),
			UsernamePasswordAuthenticationFilter.class
		);

		return http.build();
	}

}
