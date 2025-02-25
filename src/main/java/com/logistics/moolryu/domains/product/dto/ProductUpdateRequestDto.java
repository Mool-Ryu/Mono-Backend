package com.logistics.moolryu.domains.product.dto;

import com.logistics.moolryu.domains.product.enums.ProductStatus;

import lombok.Getter;

@Getter
public class ProductUpdateRequestDto {
	private String productName;

	private String description;

	private Integer price;

	private ProductStatus productStatus;
}
