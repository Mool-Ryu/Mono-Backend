package com.logistics.moolryu.domains.product.dto;

import com.logistics.moolryu.domains.product.enums.StockRequestStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class StockOrderCancelRequestDto {

	@NotBlank(message = "요청 정보는 필수입니다.")
	private Long orderStockId;

	@NotBlank(message = "유저명은 필수입니다.")
	private Long userId;

	@NotBlank(message = "상태 변경 입력은 필수입니다.")
	private StockRequestStatus stockRequestStatus;
}
