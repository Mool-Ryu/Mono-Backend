package com.logistics.moolryu.domains.user.entity;

import com.logistics.moolryu.global.security.user.UserRoleConvertor;
import com.logistics.moolryu.global.security.user.UserRoleEnum;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "p_user")
public class User {

	@Id
	@Tsid
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String password;

	@Column(nullable = false, unique = true)
	private String nickName;

	@Column(nullable = false)
	@Convert(converter = UserRoleConvertor.class)
	private UserRoleEnum role;

	@Builder
	private User(String username, String password, String nickName, UserRoleEnum role) {
		this.username = username;
		this.password = password;
		this.nickName = nickName;
		this.role = role;
	}

	public static User createUser(String username, String password, String nickName) {
		return User.builder()
			.username(username)
			.password(password)
			.nickName(nickName)
			.role(UserRoleEnum.USER)
			.build();
	}

	public static User createManager(String username, String password, String nickName, UserRoleEnum role) {
		return User.builder()
			.username(username)
			.password(password)
			.nickName(nickName)
			.role(role)
			.build();
	}
}
