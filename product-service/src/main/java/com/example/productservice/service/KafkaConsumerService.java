package com.example.productservice.service;

import com.example.productservice.entity.ProductEntity;
import com.example.productservice.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final ProductRepository productRepository;

    @KafkaListener(topics = "order-product-topic")
    @Transactional
    public void updateQty(String kafkaMessage) { //토픽에서 메세지 가져옴
        log.info("Kafka Message: ->" + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {}); // Json 형태의 string -> json
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        ProductEntity product = productRepository.findByProductId((String) map.get("productId")).orElseThrow(() -> new ServiceException("제품 정보가 올바르지 않습니다."));

        if((Integer) map.get("quantity") > product.getStock()){
            throw new ServiceException("해당 상품의 재고가 부족합니다");
            // todo :: implement rollback
        }else {
            product.setStock(product.getStock() - (Integer) map.get("quantity"));
            productRepository.save(product);
        }

    }

}
