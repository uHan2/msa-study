package com.example.userservice;

import com.example.userservice.entity.UserEntity;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
@RequiredArgsConstructor
@Slf4j
public class UserServiceApplication implements ApplicationRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserEntity user1 = UserEntity.builder()
                .userId("user1")
                .name("홍길동")
                .password("password")
                .build();

        UserEntity user2 = UserEntity.builder()
                .userId("user2")
                .name("흥부")
                .password("password")
                .build();

        UserEntity user3 = UserEntity.builder()
                .userId("user3")
                .name("놀부")
                .password("password")
                .build();

        userRepository.saveAll(Arrays.asList(user1, user2, user3));

    }
}

