package com.example.serviceregistrationanddiscoveryclient1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class ServiceRegistrationAndDiscoveryClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistrationAndDiscoveryClient1Application.class, args);
    }

}

