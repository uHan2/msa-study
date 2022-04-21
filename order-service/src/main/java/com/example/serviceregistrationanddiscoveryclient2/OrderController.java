package com.example.serviceregistrationanddiscoveryclient2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {
    @GetMapping("/order-service/health-check")
    public String info(@Value("${server.port}") String port) {
        log.info("@@@Order Service Health Check@@@");
        return "order-service 서비스의 기본 동작 Port: {" + port + "}";
    }
}
