package com.acs560.ShareTaxi.services.impl;

import com.acs560.ShareTaxi.entities.CustomUserEntity;
import com.acs560.ShareTaxi.entities.RideEntity;
import com.acs560.ShareTaxi.repositories.RideRepository;
import com.acs560.ShareTaxi.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;

    @Autowired
    public RideServiceImpl(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    @Override
    public List<RideEntity> getAllRides() {
        return rideRepository.findAll();
    }

    @Override
    public Optional<RideEntity> getRideById(Long id) {
        return rideRepository.findById(id);
    }

    @Override
    public List<RideEntity> getRidesByDriver(Long driverId) {
        return rideRepository.findByDriverId(driverId);
    }

    @Override
    public RideEntity createRide(RideEntity ride) {
        return rideRepository.save(ride);
    }

    @Override
    public RideEntity updateRide(Long id, RideEntity updatedRide) {
        return rideRepository.findById(id).map(ride -> {
            ride.setDriver(updatedRide.getDriver());
            ride.setCar(updatedRide.getCar());
            ride.setStartingPoint(updatedRide.getStartingPoint());
            ride.setDestination(updatedRide.getDestination());
            ride.setDate(updatedRide.getDate());
            ride.setStartTime(updatedRide.getStartTime());
            ride.setEndTime(updatedRide.getEndTime());
            ride.setPricePerHead(updatedRide.getPricePerHead());
            ride.setAvailableSeats(updatedRide.getAvailableSeats());
            ride.setRideStatus(updatedRide.getRideStatus());
            ride.setPassengers(updatedRide.getPassengers());
            return rideRepository.save(ride);
        }).orElseThrow(() -> new RuntimeException("Ride not found with id " + id));
    }

    @Override
    public void deleteRide(Long id) {
        rideRepository.deleteById(id);
    }

//    @Override
//    public RideEntity addPassengerToRide(Long rideId, CustomUserEntity passenger) {
//        RideEntity ride = rideRepository.findById(rideId)
//                .orElseThrow(() -> new RuntimeException("Ride not found with id " + rideId));
//        if (ride.getAvailableSeats() <= 0) {
//            throw new RuntimeException("No available seats for this ride");
//        }
//        ride.getPassengers().add(passenger);
//        ride.setAvailableSeats(ride.getAvailableSeats() - 1);
//        return rideRepository.save(ride);
//    }

//    @Override
//    public RideEntity removePassengerFromRide(Long rideId, CustomUserEntity passenger) {
//        RideEntity ride = rideRepository.findById(rideId)
//                .orElseThrow(() -> new RuntimeException("Ride not found with id " + rideId));
//        if (ride.getPassengers().contains(passenger)) {
//            ride.getPassengers().remove(passenger);
//            ride.setAvailableSeats(ride.getAvailableSeats() + 1);
//        }
//        return rideRepository.save(ride);
//    }
//
//    @Override
//    public List<RideEntity> findRidesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
//        return rideRepository.findByPricePerHeadBetween(minPrice, maxPrice);
//    }
//
//    @Override
//    public List<RideEntity> findRidesByStatus(String status) {
//        return rideRepository.findByRideStatus(status);
//    }
}
