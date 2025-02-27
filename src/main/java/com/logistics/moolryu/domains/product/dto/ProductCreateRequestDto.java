package com.logistics.moolryu.domains.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class ProductCreateRequestDto {

	@NotBlank(message = "상품명은 필수입니다.")
	private String productName;

	@NotBlank(message = "설명 입력은 필수입니다.")
	private String description;

	@Min(value = 1, message = "가격은 음수일 수 없습니다.")
	private int price;

	@Min(value = 1, message = "수량은 음수일 수 없습니다.")
	private int quantity;

	@NotBlank(message = "해당 등록자명은 필수입니다.")
	private Long userId;

}
