package com.logistics.moolryu.global.security.user;

import jakarta.persistence.AttributeConverter;

public class UserRoleConvertor implements AttributeConverter<UserRoleEnum, String> {

	@Override
	public String convertToDatabaseColumn(UserRoleEnum userRoleEnum) {
		if (userRoleEnum == null) {
			return null;
		}
		return userRoleEnum.getAuthority();
	}

	@Override
	public UserRoleEnum convertToEntityAttribute(String data) {
		return UserRoleEnum.getInstance(data);
	}
}
