package com.example.productservice.service;

import com.example.productservice.entity.ProductEntity;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductEntity> getProductList() {
        return productRepository.findAll();
    }

    public ProductEntity getProduct(String productCd) {
        return productRepository.findByProductCd(productCd);
    }

}
