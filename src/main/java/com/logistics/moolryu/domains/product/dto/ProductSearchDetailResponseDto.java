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
public class ProductSearchDetailResponseDto {

	private Long productId;

	private String productName;

	private String description;

	private int price;

	private int quantity;

	private Long userId;

	public static ProductSearchDetailResponseDto from(Product product) {
		return ProductSearchDetailResponseDto.builder()
			.productId(product.getId())
			.productName(product.getName())
			.description(product.getDescription())
			.price(product.getPrice())
			.quantity(product.getQuantity())
			.userId(product.getId())
			.build();

	}


}
