package com.logistics.moolryu.domains.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.logistics.moolryu.domains.product.dto.ProductSearchResponseDto;
import com.logistics.moolryu.domains.product.entity.QProduct;
import com.logistics.moolryu.domains.product.enums.ProductSortOption;
import com.logistics.moolryu.domains.product.enums.ProductStatus;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

	private final JPAQueryFactory jpaQueryFactory;

	QProduct qProduct = QProduct.product;

	@Override
	public Page<ProductSearchResponseDto> SearchProduct(
		Pageable pageable, String productName,
		ProductStatus productStatus,
		ProductSortOption productSortOption
	){
		List<ProductSearchResponseDto> productList = jpaQueryFactory.query().select(
			Projections.constructor(
				ProductSearchResponseDto.class,
				qProduct.id,
				qProduct.name,
				qProduct.price,
				qProduct.quantity
			)
		).from(qProduct)
			.where(
				productNameLike(productName),
				productStatus(productStatus)
			)
			.orderBy(getOrderSpecifier(productSortOption))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.fetch();

		Long total = jpaQueryFactory.select(qProduct.count())
			.from(qProduct)
			.where(
				productNameLike(productName),
				productStatus(productStatus)
			)
			.fetchOne();

		return new PageImpl<>(productList, pageable, total != null ? total : 0L);

	}

	private BooleanExpression productNameLike(String productName){
		return productName != null ? qProduct.name.like(productName) : null;
	}

	private BooleanExpression productStatus(ProductStatus productStatus){
		return productStatus != null ? qProduct.productStatus.eq(productStatus) : null;
	}

	private OrderSpecifier<?> getOrderSpecifier(ProductSortOption sortOption) {
		return switch (sortOption) {
			case PRICE_AT_DESC -> qProduct.price.desc();
			case PRICE_AT_ASC -> qProduct.price.asc();
			case QUANTITY_AT_DESC -> qProduct.quantity.desc();
			case QUANTITY_AT_ASC -> qProduct.quantity.asc();
			default -> qProduct.price.asc();
		};
	}



}
