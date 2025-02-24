package com.logistics.moolryu.domains.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.moolryu.domains.product.entity.Product;
import com.logistics.moolryu.domains.user.entity.User;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

	boolean existsByNameAndUser(String productName, User user);
}
