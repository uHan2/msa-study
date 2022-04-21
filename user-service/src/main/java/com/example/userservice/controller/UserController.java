package com.example.userservice.controller;

import com.example.userservice.entity.UserEntity;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/health-check")
    public String info(@Value("${server.port}") String port) {
        log.info("@@@User Service Health Check@@@");
        return "user-service 서비스의 기본 동작 Port: {" + port + "}";
    }

    @GetMapping("/auth")
    public String auth(@RequestHeader(value = "token") String token) {
        return "token is " + token;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUserList() {
        return new ResponseEntity<>(userService.getUserList(), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable String userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

}
