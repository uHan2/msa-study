package com.example.productservice.service;

import com.example.productservice.entity.ProductEntity;
import com.example.productservice.message.CreateOrderEvent;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final ProductRepository productRepository;

    @Bean
    @Transactional
    public Consumer<CreateOrderEvent> productUpdate() {
        return event -> {
            log.info("CreateOrderEvent 이벤트 수신");
            updateProductQuantity(event);
        };
    }

    public void updateProductQuantity(CreateOrderEvent event) {
        log.info(event.toString());
        ProductEntity product = productRepository.findByProductId(event.getProductId()).orElseThrow(() -> new ServiceException("제품 정보가 올바르지 않습니다."));
        product.setStock(product.getStock() - event.getQuantity());
        productRepository.save(product);
    }

}
