package com.example.userservice.service;

import com.example.userservice.entity.UserEntity;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserEntity> getUserList() {
        return userRepository.findAll();
    }

    public UserEntity getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

}
