package com.logistics.moolryu.domains.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.logistics.moolryu.domains.user.controller.dto.LogInRequestDto;
import com.logistics.moolryu.domains.user.controller.dto.SignUpRequestDto;
import com.logistics.moolryu.domains.user.entity.User;
import com.logistics.moolryu.domains.user.repository.UserRepository;
import com.logistics.moolryu.global.exception.CustomException;
import com.logistics.moolryu.global.exception.ErrorCode;
import com.logistics.moolryu.global.security.user.UserRoleEnum;
import com.logistics.moolryu.global.security.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public void signUp(SignUpRequestDto requestDto) {
		if (userRepository.existsByUsername(requestDto.getUsername())) {
			throw new CustomException(ErrorCode.ALREADY_EXISTS_USER);
		}

		String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

		User user = User.builder()
			.username(requestDto.getUsername())
			.password(encodedPassword)
			.nickName(requestDto.getNickname())
			.role(UserRoleEnum.USER)
			.build();

		userRepository.save(user);
	}

	public String login(LogInRequestDto requestDto) {
		User user = userRepository.findByUsername(requestDto.getUsername())
			.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

		if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
			throw new CustomException(ErrorCode.INVALID_PASSWORD);
		}

		jwtUtil.createToken(user.getUsername());

		return jwtUtil.createToken(user.getUsername());
	}


}

