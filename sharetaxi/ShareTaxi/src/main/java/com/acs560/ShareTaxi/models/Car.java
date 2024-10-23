package com.acs560.ShareTaxi.models;

import lombok.Getter;
import lombok.Setter;

/**
 * The Car class represents a vehicle in the ShareTaxi application.
 * It contains information about the car's owner, model, license plate, 
 * available seats, mileage, color, and type of car.
 * This class is primarily used for data transfer between different layers
 * of the application and is not mapped directly to the database.
 */
@Getter
@Setter
public class Car {

    /**
     * Unique identifier for the car.
     */
    private Long id;

    /**
     * Identifier for the owner of the car.
     */
    private Long ownerId;

    /**
     * The model or make of the car (e.g., Toyota Prius).
     */
    private String model;

    /**
     * The car's license plate number.
     */
    private String licensePlate;

    /**
     * The number of seats available in the car.
     */
    private int seatsAvailable;

    /**
     * The car's mileage (in kilometers or miles, depending on the locale).
     */
    private double mileage;

    /**
     * The color of the car (e.g., red, blue, white).
     */
    private String color;

    /**
     * The type of the car (e.g., sedan, SUV, hatchback).
     */
    private String carType;
}
