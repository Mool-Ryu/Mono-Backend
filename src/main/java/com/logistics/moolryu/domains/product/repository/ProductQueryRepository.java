package com.logistics.moolryu.domains.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.logistics.moolryu.domains.product.dto.ProductSearchResponseDto;
import com.logistics.moolryu.domains.product.enums.ProductSortOption;
import com.logistics.moolryu.domains.product.enums.ProductStatus;

public interface ProductQueryRepository {
	Page<ProductSearchResponseDto> SearchProduct(
		Pageable pageable,
		String productName,
		ProductStatus productStatus,
		ProductSortOption productSortOption
	);
}
