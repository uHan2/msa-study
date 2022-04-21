package com.example.serviceregistrationanddiscoveryclient1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthCheckController {

    @GetMapping("/service1/info")
    public String info(@Value("${server.port}") String port) {
        log.info("@@@health check2@@@");
        return "eureka-client-1 서비스의 기본 동작 Port: {" + port + "}";
    }

    @GetMapping("/service1/auth")
    public String auth(@RequestHeader(value = "token") String token) {
        return "token is " + token;
    }

}
