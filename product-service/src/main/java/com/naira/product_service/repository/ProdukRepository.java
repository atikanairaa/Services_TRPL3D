package com.naira.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naira.product_service.model.Produk;

public interface ProdukRepository extends JpaRepository<Produk, Long> {

}
