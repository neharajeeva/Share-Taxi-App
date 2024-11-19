package com.acs560.ShareTaxi.services;

import com.acs560.ShareTaxi.entities.RideEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RideService {
    List<RideEntity> getAllRides();

    Optional<RideEntity> getRideById(Long id);

    //List<RideEntity> getRidesByDriverId(Long driverId);

    //List<RideEntity> getRidesByStartingPointAndDestination(String startingPoint, String destination);

    //List<RideEntity> getRidesByDate(LocalDate date);

    //List<RideEntity> getRidesWithAvailableSeats(int minimumSeats);

    //List<RideEntity> getRidesByTimeRange(LocalDateTime startTime, LocalDateTime endTime);

    //List<RideEntity> getRidesByStatus(String status);

    RideEntity createRide(RideEntity ride);

    RideEntity updateRide(Long id, RideEntity updatedRide);

    void deleteRide(Long id);

	List<RideEntity> getRidesByDriver(Long driverId);

	//List<RideEntity> getRidesByDriver(Long driverId);
}