package com.logistics.moolryu.domains.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.moolryu.domains.product.entity.StockOrder;

public interface StockOrderJpaRepository extends JpaRepository<StockOrder, Long> {
}
