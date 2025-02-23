package com.logistics.moolryu.domains.user.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.moolryu.domains.user.controller.dto.LogInRequestDto;
import com.logistics.moolryu.domains.user.controller.dto.SignUpRequestDto;
import com.logistics.moolryu.domains.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(
		@RequestBody SignUpRequestDto requestDto
	) {
		userService.signUp(requestDto);
		return ResponseEntity.ok("회원 가입 완료");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(
		@RequestBody LogInRequestDto requestDto
	) {
		String token = userService.login(requestDto);
		return ResponseEntity.ok()
			.header(HttpHeaders.AUTHORIZATION, token)
			.build();
	}
}
