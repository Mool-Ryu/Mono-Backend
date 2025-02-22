package com.logistics.moolryu.domains.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.moolryu.domains.product.entity.Product;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}
