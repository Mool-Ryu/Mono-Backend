package com.logistics.moolryu.domains.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.moolryu.domains.product.dto.ProductCreateRequestDto;
import com.logistics.moolryu.domains.product.dto.ProductCreateResponseDto;
import com.logistics.moolryu.domains.product.entity.Product;
import com.logistics.moolryu.domains.product.service.ProductService;
import com.logistics.moolryu.global.dto.SuccessResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

	private final ProductService productService;

	@PostMapping()
	public ResponseEntity<SuccessResponseDto<ProductCreateResponseDto>> createProduct(
		@RequestBody ProductCreateRequestDto request
	){
		// TODO: 2025-02-22 User 추가 예정

		return ResponseEntity.status(HttpStatus.OK)
			.body(
				SuccessResponseDto.success(
					"물품 등록 성공",
					productService.createProduct(request)
				)
			);
	}
}
