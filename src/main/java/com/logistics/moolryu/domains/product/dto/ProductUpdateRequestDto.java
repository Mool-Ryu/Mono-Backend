package com.logistics.moolryu.domains.product.dto;

import com.logistics.moolryu.domains.product.enums.ProductStatus;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ProductUpdateRequestDto {

	@Nullable
	private String productName;

	@Nullable
	private String description;

	@Nullable
	@Min(value = 1, message = "최소가격은 1원 이상입니다.")
	private Integer price;

	@Nullable
	private ProductStatus productStatus;

	@NotBlank(message = "해당 등록자명은 필수입니다.")
	private Long userId;
}
