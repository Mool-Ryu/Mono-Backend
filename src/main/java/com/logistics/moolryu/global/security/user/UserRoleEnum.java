package com.logistics.moolryu.global.security.user;

import java.util.Arrays;

public enum UserRoleEnum {

	USER(Authority.USER),
	MANAGER_USER(Authority.MANAGER_USER),
	MANAGER_PAYMENT(Authority.MANAGER_PAYMENT),
	MANAGER_ORDER(Authority.MANAGER_ORDER),
	MANAGER_PRODUCT(Authority.MANAGER_PRODUCT),
	MANAGER_DELIVERY(Authority.MANAGER_DELIVERY),
	MANAGER_HUB(Authority.MANAGER_HUB);


	private final String authority;

	UserRoleEnum(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return this.authority;
	}

	public static UserRoleEnum getInstance(String authority) {
		return Arrays.stream(values())
			.filter(role -> role.getAuthority().equals(authority))
			.findFirst()
			.orElseThrow();
	}

	public static class Authority {
		public static final String USER = "ROLE_USER";
		public static final String MANAGER_USER = "ROLE_MANAGER_USER";
		public static final String MANAGER_PAYMENT = "ROLE_MANAGER_PAYMENT";
		public static final String MANAGER_ORDER = "ROLE_MANAGER_ORDER";
		public static final String MANAGER_PRODUCT = "ROLE_MANAGER_PRODUCT";
		public static final String MANAGER_DELIVERY = "ROLE_MANAGER_DELIVERY";
		public static final String MANAGER_HUB = "ROLE_MANAGER_HUB";
	}

}
