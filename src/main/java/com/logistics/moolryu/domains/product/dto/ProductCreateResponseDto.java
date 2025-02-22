package com.logistics.moolryu.domains.product.dto;

import com.logistics.moolryu.domains.product.entity.Product;
import com.logistics.moolryu.domains.product.enums.ProductStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProductCreateResponseDto {

	private Long Id;

	private String productName;

	private String description;

	private int price;

	private int quantity;

	private ProductStatus productStatus;

	public static ProductCreateResponseDto from(Product product){
		return ProductCreateResponseDto.builder()
			.Id(product.getId())
			.productName(product.getName())
			.description(product.getDescription())
			.price(product.getPrice())
			.quantity(product.getQuantity())
			.productStatus(product.getProductStatus())
			.build();
	}

}
