package com.acs560.ShareTaxi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * The RideEntity class represents a ride being offered in the ShareTaxi system.
 * It is mapped to the "rides" table in the database and contains details about the
 * driver, car, route, timing, pricing, available seats, and passengers.
 */
@Entity
@Table(name = "rides")
@Getter
@Setter
@ToString
public class RideEntity {

    /**
     * Unique identifier for the ride.
     * This value is auto-generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The driver offering the ride.
     * This is a foreign key reference to the CustomUserEntity table.
     */
    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private CustomUserEntity driver;

    /**
     * The car used for the ride.
     * This is a foreign key reference to the CarEntity table.
     */
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private CarEntity car;

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
    @Column(precision = 6, scale = 2)
    private BigDecimal pricePerHead;

    /**
     * The number of seats available for passengers in the ride.
     */
    private int availableSeats;

    /**
     * The current status of the ride (e.g., "Yet to Start", "Ongoing", "Completed").
     */
    @Column(length = 50)
    private String rideStatus = "Yet to Start";

    /**
     * A set of passengers who have joined the ride.
     * This is represented by their user IDs.
     */
    @ManyToMany
    @JoinTable(
        name = "ride_passengers",
        joinColumns = @JoinColumn(name = "ride_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<CustomUserEntity> passengers = new HashSet<>();
}
