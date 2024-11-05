package com.acs560.ShareTaxi.services.impl;

import com.acs560.ShareTaxi.entities.CustomUserEntity;
import com.acs560.ShareTaxi.models.CustomUser;
import com.acs560.ShareTaxi.repositories.CustomUserRepository;
import com.acs560.ShareTaxi.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserServiceImpl implements CustomUserService {
    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomUser registerUser(String username, String password) {
        if (customUserRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already exists!");
        }
        CustomUserEntity userEntity = new CustomUserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setRole("USER");

        CustomUserEntity savedUser = customUserRepository.save(userEntity);
        return toModel(savedUser);
    }

    @Override
    public CustomUser authenticateUser(String username, String password) {
        CustomUserEntity userEntity = customUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new RuntimeException("Invalid credentials!");
        }
        return toModel(userEntity);
    }

    private CustomUser toModel(CustomUserEntity userEntity) {
        return new CustomUser(userEntity.getId(), userEntity.getUsername(), userEntity.getRole());
    }
}
