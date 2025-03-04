package com.logistics.moolryu.domains.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class StockOrderRequestDto {


	@Min(value = 1, message = "최소 추가 수량은 1이상입니다.")
	private int requestQuantity;

	@NotBlank(message = "해당 등록자명은 필수입니다.")
	private Long userId;



}
