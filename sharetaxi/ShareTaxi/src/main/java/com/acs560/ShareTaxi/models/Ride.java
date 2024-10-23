package com.acs560.ShareTaxi.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * The Ride class represents a ride being offered in the ShareTaxi application.
 * It includes details about the driver, car, route, timing, price, available seats,
 * and the passengers who have joined the ride.
 * This class is primarily used for data transfer between different layers
 * of the application and is not mapped directly to the database.
 */
@Getter
@Setter
public class Ride {

    /**
     * Unique identifier for the ride.
     */
    private Long id;

    /**
     * Identifier for the driver offering the ride.
     */
    private Long driverId;

    /**
     * Identifier for the car used in the ride.
     */
    private Long carId;

    /**
     * The starting point of the ride.
     */
    private String startingPoint;

    /**
     * The destination of the ride.
     */
    private String destination;

    /**
     * The date on which the ride is scheduled.
     */
    private LocalDate date;

    /**
     * The time when the ride is scheduled to start.
     */
    private LocalDateTime startTime;

    /**
     * The time when the ride is expected to end.
     */
    private LocalDateTime endTime;

    /**
     * The price per head for passengers joining the ride.
     */
    private BigDecimal pricePerHead;

    /**
     * The number of seats available for passengers.
     */
    private int availableSeats;

    /**
     * The current status of the ride (e.g., "Yet to Start", "Ongoing", "Completed").
     */
    private String rideStatus;

    /**
     * A set of identifiers for passengers who have joined the ride.
     */
    private Set<Long> passengerIds;
}