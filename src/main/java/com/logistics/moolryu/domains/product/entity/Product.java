package com.logistics.moolryu.domains.product.entity;

import java.time.LocalDateTime;

import com.logistics.moolryu.domains.common.entity.BaseTime;
import com.logistics.moolryu.domains.product.enums.ProductStatus;
import com.logistics.moolryu.domains.user.entity.User;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Product extends BaseTime {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public static Product create(String name, String description, int price, int quantity, User user){
		return Product.builder()
			.name(name)
			.description(description)
			.price(price)
			.quantity(quantity)
			.productStatus(ProductStatus.AVAILABLE)
			.user(user)
			.build();
	}

	public void setCreateBy(User user){
		setCreatedBy(user.getId());
	}

	public void setUpdateBy(User user){
		setUpdatedBy(user.getId());
	}

	public void setDeleteAtAndDeleteBy(User user) {
		setDeletedAt(LocalDateTime.now());
		setDeletedBy(user.getId());

	}

	public void update(String productName, String description, ProductStatus productStatus, Integer price){
		if(productName != null){
			this.name = productName;
		}
		if(description != null){
			this.description = description;
		}
		if(productStatus != null){
			this.productStatus = productStatus;
		}
		if(price != null){
			this.price = price;
		}

	}




}
