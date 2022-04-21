package com.example.productservice.controller;

import com.example.productservice.entity.ProductEntity;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-service")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/health-check")
    public String info(@Value("${server.port}") String port) {
        log.info("@@@Product Service Health Check@@@");
        return "user-service 서비스의 기본 동작 Port: {" + port + "}";
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductEntity>> getProductList() {
        return new ResponseEntity<>(productService.getProductList(), HttpStatus.OK);
    }

    @GetMapping("/products/{productCd}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable String productCd) {
        return new ResponseEntity<>(productService.getProduct(productCd), HttpStatus.OK);
    }

}
