package com.acs560.ShareTaxi.controllers;

import com.acs560.ShareTaxi.entities.CarEntity;
import com.acs560.ShareTaxi.entities.CustomUserEntity;
import com.acs560.ShareTaxi.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<CarEntity> addCar(@RequestBody CarEntity car) {
        CarEntity createdCar = carService.addCar(car);
        return ResponseEntity.ok(createdCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarEntity> updateCar(@PathVariable Long id, @RequestBody CarEntity updatedCar) {
        CarEntity car = carService.updateCar(id, updatedCar);
        return ResponseEntity.ok(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok("Car with ID " + id + " deleted successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarEntity> getCarById(@PathVariable Long id) {
        CarEntity car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping
    public ResponseEntity<List<CarEntity>> getAllCars() {
        List<CarEntity> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }
    
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<CarEntity>> getCarsByOwner(@PathVariable Long ownerId) {
        CustomUserEntity owner = new CustomUserEntity();
        owner.setId(ownerId); // Assuming CustomUserEntity has a field `id`
        List<CarEntity> cars = carService.getCarsByOwner(owner);
        return ResponseEntity.ok(cars);
    }
}
