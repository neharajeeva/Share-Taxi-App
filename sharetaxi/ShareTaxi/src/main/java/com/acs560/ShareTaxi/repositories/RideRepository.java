package com.acs560.ShareTaxi.repositories;

import com.acs560.ShareTaxi.entities.RideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
 * This repository is for handling all the functionalities related to the Ride
 */
@Repository
public interface RideRepository extends JpaRepository<RideEntity, Long> {

    // Find rides by driver ID
    List<RideEntity> findByDriverId(Long driverId);

    // Find rides by starting point and destination
    List<RideEntity> findByStartingPointAndDestination(String startingPoint, String destination);

    // Find rides scheduled for a specific date
    List<RideEntity> findByDate(LocalDate date);

    // Find rides that have available seats
    List<RideEntity> findByAvailableSeatsGreaterThan(int availableSeats);

    // Find rides within a specific time range
    List<RideEntity> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    // Find rides by ride status
    List<RideEntity> findByRideStatus(String rideStatus);
}
