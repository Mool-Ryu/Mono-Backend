package com.logistics.moolryu.domains.user.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

	@NotBlank(message = "유저네임 입력")
	@Size(min = 6, max = 15, message = "유저네임은 6자 이상 15자 이하")
	@Pattern(regexp = "^[a-z0-9]+$", message = "유저네임은 영문 소문자와 숫자만 가능")
	private String username;

	@NotBlank(message = "비밀번호 입력")
	@Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이하")
	@Pattern(regexp = "^[a-z0-9]+$", message = "비밀번호는 영문 소문자와 숫자만 가능")
	private String password;

	@NotBlank(message = "닉네임 입력")
	@Size(min = 3, max = 16, message = "닉네임은 3자 이상 16자 이하")
	@Pattern(regexp = "^[a-zA-Z0-9가-힣]+$", message = "닉네임은 영문 대소문자, 한글, 숫자만 가능")
	private String nickname;

}
