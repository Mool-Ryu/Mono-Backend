package com.logistics.moolryu.domains.product.entity;

import com.logistics.moolryu.domains.product.enums.ProductStatus;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "p_product")
public class Product {
	@Tsid
	@Id
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "price", nullable = false)
	private int price;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@Enumerated(EnumType.STRING)
	@Column(name = "product_status", nullable = false)
	private ProductStatus productStatus;

	public static Product create(String name, String description, int price, int quantity){
		return Product.builder()
			.name(name)
			.description(description)
			.price(price)
			.quantity(quantity)
			.productStatus(ProductStatus.AVAILABLE)
			.build();
	}



}
