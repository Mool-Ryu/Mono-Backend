package com.logistics.moolryu.domains.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public class ProductCreateRequestDto {

	private String productName;

	private String description;

	private int price;

	private int quantity;

}
