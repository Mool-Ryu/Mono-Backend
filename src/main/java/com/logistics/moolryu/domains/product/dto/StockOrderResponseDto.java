package com.logistics.moolryu.domains.product.dto;

import com.logistics.moolryu.domains.product.entity.StockOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockOrderResponseDto {

	private Long productId;

	private Long stockOrderId;

	private Integer requestQuantity;

	public static StockOrderResponseDto from(StockOrder stockOrder){
		return StockOrderResponseDto.builder()
			.productId(stockOrder.getProduct().getId())
			.stockOrderId(stockOrder.getId())
			.requestQuantity(stockOrder.getRequestQuantity())
			.build();
	}


}
