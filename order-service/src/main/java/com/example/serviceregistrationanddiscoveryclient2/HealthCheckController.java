package com.example.serviceregistrationanddiscoveryclient2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthCheckController {
    @GetMapping("/service2/info")
    public String info(@Value("${server.port}") String port) {
        log.info("@@@health check2@@@");
        return "eureka-client-2 서비스의 기본 동작 Port: {" + port + "}";
    }
}
