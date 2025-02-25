package com.logistics.moolryu.domains.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistics.moolryu.domains.product.dto.ProductCreateRequestDto;
import com.logistics.moolryu.domains.product.dto.ProductCreateResponseDto;
import com.logistics.moolryu.domains.product.dto.ProductSearchDetailResponseDto;
import com.logistics.moolryu.domains.product.dto.ProductSearchResponseDto;
import com.logistics.moolryu.domains.product.dto.ProductUpdateRequestDto;
import com.logistics.moolryu.domains.product.dto.StockOrderRequestDto;
import com.logistics.moolryu.domains.product.dto.StockOrderResponseDto;
import com.logistics.moolryu.domains.product.entity.Product;
import com.logistics.moolryu.domains.product.entity.StockOrder;
import com.logistics.moolryu.domains.product.enums.ProductSortOption;
import com.logistics.moolryu.domains.product.enums.ProductStatus;
import com.logistics.moolryu.domains.product.repository.ProductJpaRepository;
import com.logistics.moolryu.domains.product.repository.ProductQueryRepository;
import com.logistics.moolryu.domains.product.repository.StockOrderJpaRepository;
import com.logistics.moolryu.domains.user.entity.User;
import com.logistics.moolryu.global.exception.CustomException;
import com.logistics.moolryu.global.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductJpaRepository productJpaRepository;
	private final ProductQueryRepository productQueryRepository;
	private final StockOrderJpaRepository stockOrderJpaRepository;

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

	@Transactional(readOnly = true)
	public ProductSearchDetailResponseDto searchDetailProduct(Long productId){
		Product product = findById(productId);
		return ProductSearchDetailResponseDto.from(product);
	}

	@Transactional
	public void updateProduct(Long productId, ProductUpdateRequestDto requestDto, User user){

		Product product = findByIdAndUser(productId, user);

		product.update(
			requestDto.getProductName(),
			requestDto.getDescription(),
			requestDto.getProductStatus(),
			requestDto.getPrice()
		);
	}

	@Transactional
	public StockOrderResponseDto createStockOrder(Long productId, StockOrderRequestDto requestDto, User user){
		Product product = findByIdAndUser(productId, user);

		StockOrder stockOrder = StockOrder.create(requestDto.getRequestQuantity(), product);

		stockOrder = stockOrderJpaRepository.save(stockOrder);

		return StockOrderResponseDto.from(stockOrder);
	}



	private Product findById(Long productId){
		return productJpaRepository.findById(productId).orElseThrow(
			() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT)
		);
	}

	private Product findByIdAndUser(Long productId, User user){
		return productJpaRepository.findByIdAndUser(productId, user).orElseThrow(
			() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT)
		);
	}


	private void validateProduct(String productName, User user){
		if(productJpaRepository.existsByNameAndUser(productName, user)){
			throw new CustomException(ErrorCode.ALREADY_EXISTS_PRODUCT);
		}
	}


}
