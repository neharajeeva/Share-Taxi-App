package com.acs560.ShareTaxi.repositories;

import com.acs560.ShareTaxi.entities.CarEntity;
import com.acs560.ShareTaxi.entities.CustomUserEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
    // Additional query methods can be added here if needed
	List<CarEntity> findByOwner(CustomUserEntity owner);
}
