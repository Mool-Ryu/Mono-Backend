package com.logistics.moolryu.domains.product.dto;

import lombok.Getter;


@Getter
public class ProductCreateRequestDto {

	private String productName;

	private String description;

	private int price;

	private int quantity;

	private Long userId;

}
