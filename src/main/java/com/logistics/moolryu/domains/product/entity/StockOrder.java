package com.logistics.moolryu.domains.product.entity;

import com.logistics.moolryu.domains.common.entity.BaseTime;
import com.logistics.moolryu.domains.product.enums.StokeRequestStatus;
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
@Table(name = "p_stock_order")
public class StockOrder extends BaseTime {

	@Id
	@Tsid
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "request_quantity", nullable = false)
	private Integer requestQuantity;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private StokeRequestStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public static StockOrder create(Integer requestQuantity, Product product){
		return StockOrder.builder()
			.requestQuantity(requestQuantity)
			.product(product)
			.status(StokeRequestStatus.PENDING)
			.build();
	}

	public void updateStatus(){
		this.status = StokeRequestStatus.PROCESSED;
	}

	public void setCreateByAndUpdateBy(User user){
		setCreatedBy(user.getId());
		setUpdatedBy(user.getId());
	}


}
