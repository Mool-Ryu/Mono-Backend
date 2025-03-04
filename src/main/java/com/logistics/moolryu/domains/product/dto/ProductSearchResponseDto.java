package com.logistics.moolryu.domains.product.dto;

import com.logistics.moolryu.domains.product.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSearchResponseDto {

	private Long productId;

	private String productName;

	private int price;

	private int quantity;

	public static ProductSearchResponseDto from(Product product){
		return ProductSearchResponseDto.builder()
			.productId(product.getId())
			.productName(product.getName())
			.price(product.getPrice())
			.quantity(product.getQuantity())
			.build();
	}


}
