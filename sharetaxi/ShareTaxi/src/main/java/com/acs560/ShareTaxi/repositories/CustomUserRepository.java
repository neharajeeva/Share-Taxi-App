package com.acs560.ShareTaxi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acs560.ShareTaxi.entities.CustomUserEntity;

import java.util.Optional;

public interface CustomUserRepository extends JpaRepository<CustomUserEntity, Long> {
    Optional<CustomUserEntity> findByUsername(String username);
}
