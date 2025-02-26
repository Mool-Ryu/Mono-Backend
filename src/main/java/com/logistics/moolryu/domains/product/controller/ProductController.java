package com.logistics.moolryu.domains.product.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.moolryu.domains.product.dto.ProductCreateRequestDto;
import com.logistics.moolryu.domains.product.dto.ProductCreateResponseDto;
import com.logistics.moolryu.domains.product.dto.ProductSearchDetailResponseDto;
import com.logistics.moolryu.domains.product.dto.ProductSearchResponseDto;
import com.logistics.moolryu.domains.product.dto.ProductUpdateRequestDto;
import com.logistics.moolryu.domains.product.dto.StockOrderRequestDto;
import com.logistics.moolryu.domains.product.dto.StockOrderResponseDto;
import com.logistics.moolryu.domains.product.enums.ProductSortOption;
import com.logistics.moolryu.domains.product.enums.ProductStatus;
import com.logistics.moolryu.domains.product.service.ProductService;
import com.logistics.moolryu.global.dto.SuccessResponseDto;
import com.logistics.moolryu.global.security.user.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	@PreAuthorize("hasAnyRole('MANAGER_PRODUCT')")
	@PostMapping
	public ResponseEntity<SuccessResponseDto<ProductCreateResponseDto>> createProduct(
		@RequestBody ProductCreateRequestDto request,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(
				SuccessResponseDto.success(
					"물품 등록 성공",
					productService.createProduct(request, userDetails.getUser())
				)
			);
	}

	@GetMapping
	public ResponseEntity<SuccessResponseDto<Page<ProductSearchResponseDto>>> searchProduct(
		Pageable pageable,
		@RequestParam(required = false) String productName,
		@RequestParam(required = false) ProductStatus productStatus,
		@RequestParam(required = false) ProductSortOption productSortOption
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(
				SuccessResponseDto.success(
					"물품 전체 조회 성공",
					productService.searchProduct(pageable, productName, productStatus, productSortOption)
				)
			);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<SuccessResponseDto<ProductSearchDetailResponseDto>> searchDetailProduct(
		@PathVariable Long productId
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(
				SuccessResponseDto.success(
					"물품 단건 조회 성공",
					productService.searchDetailProduct(productId)
				)
			);

	}

	@PreAuthorize("hasAnyRole('MANAGER_PRODUCT')")
	@PatchMapping("/{productId}")
	public ResponseEntity<SuccessResponseDto<Void>> updateProduct(
		@PathVariable Long productId,
		@RequestBody ProductUpdateRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		productService.updateProduct(productId, requestDto, userDetails.getUser());
		return ResponseEntity.status(HttpStatus.OK)
			.body(
				SuccessResponseDto.success(
					"물품 수정 성공"
				)
			);

	}

	@PreAuthorize("hasAnyRole('MANAGER_PRODUCT')")
	@PostMapping("/{productId}")
	public ResponseEntity<SuccessResponseDto<StockOrderResponseDto>> createOrderStock(
		@PathVariable Long productId,
		@RequestBody StockOrderRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(
				SuccessResponseDto.success(
					"재고 추가 요청 성공",
					productService.createStockOrder(productId, requestDto, userDetails.getUser())
				)
			);
	}

}
