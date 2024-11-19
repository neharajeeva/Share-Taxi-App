package com.acs560.ShareTaxi.services;

import com.acs560.ShareTaxi.entities.RideEntity;
import com.acs560.ShareTaxi.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RideService {

    private final RideRepository rideRepository;

    @Autowired
    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public List<RideEntity> getAllRides() {
        return rideRepository.findAll();
    }

    public Optional<RideEntity> getRideById(Long id) {
        return rideRepository.findById(id);
    }

    public List<RideEntity> getRidesByDriverId(Long driverId) {
        return rideRepository.findByDriverId(driverId);
    }

    public List<RideEntity> getRidesByStartingPointAndDestination(String startingPoint, String destination) {
        return rideRepository.findByStartingPointAndDestination(startingPoint, destination);
    }

    public List<RideEntity> getRidesByDate(LocalDate date) {
        return rideRepository.findByDate(date);
    }

    public List<RideEntity> getRidesWithAvailableSeats(int minimumSeats) {
        return rideRepository.findByAvailableSeatsGreaterThan(minimumSeats);
    }
    public List<RideEntity> getRidesByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return rideRepository.findByStartTimeBetween(startTime, endTime);
    }

    public List<RideEntity> getRidesByStatus(String status) {
        return rideRepository.findByRideStatus(status);
    }

    public RideEntity createRide(RideEntity ride) {
        return rideRepository.save(ride);
    }

    public RideEntity updateRide(Long id, RideEntity updatedRide) {
        return rideRepository.findById(id).map(ride -> {
            ride.setDriverId(updatedRide.getDriverId());
            ride.setStartingPoint(updatedRide.getStartingPoint());
            ride.setDestination(updatedRide.getDestination());
            ride.setDate(updatedRide.getDate());
            ride.setStartTime(updatedRide.getStartTime());
            ride.setAvailableSeats(updatedRide.getAvailableSeats());
            ride.setRideStatus(updatedRide.getRideStatus());
            return rideRepository.save(ride);
        }).orElseThrow(() -> new RuntimeException("Ride not found with id " + id));
    }

    public void deleteRide(Long id) {
        rideRepository.deleteById(id);
    }
}

