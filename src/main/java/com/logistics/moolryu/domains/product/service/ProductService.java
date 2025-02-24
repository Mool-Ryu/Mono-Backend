package com.logistics.moolryu.domains.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistics.moolryu.domains.product.dto.ProductCreateRequestDto;
import com.logistics.moolryu.domains.product.dto.ProductCreateResponseDto;
import com.logistics.moolryu.domains.product.dto.ProductSearchResponseDto;
import com.logistics.moolryu.domains.product.entity.Product;
import com.logistics.moolryu.domains.product.enums.ProductSortOption;
import com.logistics.moolryu.domains.product.enums.ProductStatus;
import com.logistics.moolryu.domains.product.repository.ProductJpaRepository;
import com.logistics.moolryu.domains.product.repository.ProductQueryRepository;
import com.logistics.moolryu.domains.user.entity.User;
import com.logistics.moolryu.global.exception.CustomException;
import com.logistics.moolryu.global.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductJpaRepository productJpaRepository;
	private final ProductQueryRepository productQueryRepository;

	@Transactional
	public ProductCreateResponseDto createProduct(ProductCreateRequestDto requestDto, User user) {

		validateProduct(requestDto.getProductName(), user);

		Product product = Product.create(
			requestDto.getProductName(),
			requestDto.getDescription(),
			requestDto.getPrice(),
			requestDto.getQuantity(),
			user
		);

		product = productJpaRepository.save(product);

		return ProductCreateResponseDto.from(product);

	}

	@Transactional(readOnly = true)
	public Page<ProductSearchResponseDto> searchProduct(
		Pageable pageable,
		String productName,
		ProductStatus productStatus,
		ProductSortOption productSortOption
	){
		return productQueryRepository.SearchProduct(pageable, productName, productStatus, productSortOption);
	}


	private void validateProduct(String productName, User user){
		if(productJpaRepository.existsByNameAndUser(productName, user)){
			throw new CustomException(ErrorCode.ALREADY_EXISTS_PRODUCT);
		}
	}

}
