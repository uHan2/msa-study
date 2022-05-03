package com.example.productservice.service;

import com.example.productservice.entity.ProductEntity;
import com.example.productservice.message.CancelOrderEvent;
import com.example.productservice.message.CreateOrderEvent;
import com.example.productservice.message.EventType;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private final StreamBridge streamBridge;

    @Transactional(readOnly = true)
    public List<ProductEntity> getProductList() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ProductEntity getProduct(String productId) {
        return productRepository.findByProductId(productId).orElseThrow(() -> new ServiceException("제품 정보가 올바르지 않습니다."));
    }

    @Transactional
    public void decreaseProduct(CreateOrderEvent event) {
        log.info("CreateOrderEvent 이벤트 수신 : " + event.toString());
        ProductEntity product = productRepository.findByProductId(event.getProductId()).orElseThrow(() -> new ServiceException("제품 정보가 올바르지 않습니다."));

        if (product.getStock() - event.getQuantity() < 0) {
            streamBridge.send("cancelOrder-out-0", new CancelOrderEvent(event));
            log.info("CancelOrderEvent 이벤트 발행 : " + new CancelOrderEvent(event));
            return;
        }
        product.setStock(product.getStock() - event.getQuantity());
        productRepository.save(product);
    }

    @Transactional
    public void increaseProduct(CancelOrderEvent event) {
        log.info("CancelOrderEvent 이벤트 수신 : " + event.toString());
        ProductEntity product = productRepository.findByProductId(event.getProductId()).orElseThrow(() -> new ServiceException("제품 정보가 올바르지 않습니다."));
        if (event.getEventType().equals(EventType.USER_CANCEL)) {
            product.setStock(product.getStock() + event.getQuantity());
            productRepository.save(product);
        }
    }
}
