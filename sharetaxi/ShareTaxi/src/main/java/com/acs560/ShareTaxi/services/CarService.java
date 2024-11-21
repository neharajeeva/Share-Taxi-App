package com.acs560.ShareTaxi.services;

import com.acs560.ShareTaxi.entities.CarEntity;
import com.acs560.ShareTaxi.entities.CustomUserEntity;

import java.util.List;

public interface CarService {
    CarEntity addCar(CarEntity car);

    CarEntity updateCar(Long id, CarEntity updatedCar);

    void deleteCar(Long id);

    CarEntity getCarById(Long id);

    List<CarEntity> getAllCars();
    
    List<CarEntity> getCarsByOwner(CustomUserEntity owner);
}
