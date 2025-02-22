package com.logistics.moolryu.domains.product.service;

import org.springframework.stereotype.Service;

import com.logistics.moolryu.domains.product.dto.ProductCreateRequestDto;
import com.logistics.moolryu.domains.product.dto.ProductCreateResponseDto;
import com.logistics.moolryu.domains.product.entity.Product;
import com.logistics.moolryu.domains.product.repository.ProductJpaRepository;
import com.logistics.moolryu.domains.product.repository.ProductQueryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductJpaRepository productJpaRepository;
	private final ProductQueryRepository productQueryRepository;

	public ProductCreateResponseDto createProduct(ProductCreateRequestDto request) {
		// TODO: 2025-02-22  user 추가 예정 = 검증
		Product product = Product.create(
			request.getProductName(),
			request.getDescription(),
			request.getPrice(),
			request.getQuantity());

		product = productJpaRepository.save(product);

		return ProductCreateResponseDto.from(product);

	}


}
