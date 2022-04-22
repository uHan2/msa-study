package com.example.productservice.service;

import com.example.productservice.entity.ProductEntity;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductEntity> getProductList() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ProductEntity getProduct(String productId) {
        return productRepository.findByProductId(productId).orElseThrow(() -> new ServiceException("제품 정보가 올바르지 않습니다."));
    }

}
