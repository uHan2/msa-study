package com.example.productservice.repository;

import com.example.productservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByProductCd(String ProductCd);
}
