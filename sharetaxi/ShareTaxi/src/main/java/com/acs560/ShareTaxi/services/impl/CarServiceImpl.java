package com.acs560.ShareTaxi.services.impl;

import com.acs560.ShareTaxi.entities.CarEntity;
import com.acs560.ShareTaxi.entities.CustomUserEntity;
import com.acs560.ShareTaxi.repositories.CarRepository;
import com.acs560.ShareTaxi.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public CarEntity addCar(CarEntity car) {
        return carRepository.save(car);
    }

    @Override
    public CarEntity updateCar(Long id, CarEntity updatedCar) {
        Optional<CarEntity> existingCar = carRepository.findById(id);
        if (existingCar.isPresent()) {
            CarEntity car = existingCar.get();
            car.setModel(updatedCar.getModel());
            car.setLicensePlate(updatedCar.getLicensePlate());
            car.setSeatsAvailable(updatedCar.getSeatsAvailable());
            car.setMileage(updatedCar.getMileage());
            car.setColor(updatedCar.getColor());
            car.setCarType(updatedCar.getCarType());
            car.setOwner(updatedCar.getOwner());
            return carRepository.save(car);
        } else {
            throw new RuntimeException("Car with ID " + id + " not found.");
        }
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarEntity getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Car with ID " + id + " not found."));
    }

    @Override
    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }
    
    @Override
    public List<CarEntity> getCarsByOwner(CustomUserEntity owner) {
        return carRepository.findByOwner(owner);
    }
}
