package com.acs560.ShareTaxi.controllers;

import com.acs560.ShareTaxi.entities.RideEntity;
import com.acs560.ShareTaxi.repositories.RideRepository;
import com.acs560.ShareTaxi.services.RideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideRepository rideRepository;
    
    @Autowired
    private RideService rideService;

    // Get all rides
    @GetMapping
    public List<RideEntity> getAllRides() {
        return rideRepository.findAll();
    }

    // Get rides by driver ID
    @GetMapping("/driver/{driverId}")
    public List<RideEntity> getRidesByDriverId(@PathVariable("driverId") Long driverId) {
        return rideRepository.findByDriverId(driverId);
    }

    // Get rides by starting point and destination
    @GetMapping("/route")
    public List<RideEntity> getRidesByRoute(@RequestParam String startingPoint, @RequestParam String destination) {
        return rideRepository.findByStartingPointAndDestination(startingPoint, destination);
    }

    // Get rides scheduled for a specific date
    @GetMapping("/date/{date}")
    public List<RideEntity> getRidesByDate(@PathVariable("date") LocalDate date) {
        return rideRepository.findByDate(date);
    }

    // Get rides with available seats
    @GetMapping("/available-seats/{seats}")
    public List<RideEntity> getRidesByAvailableSeats(@PathVariable("seats") int seats) {
        return rideRepository.findByAvailableSeatsGreaterThan(seats);
    }
//
//    // Get rides within a specific time range
//    @GetMapping("/time-range")
//    public List<RideEntity> getRidesByTimeRange(@RequestParam String startTime, @RequestParam String endTime) {
//        return rideRepository.findByStartTimeBetween(startTime, endTime);
//    }

    // Get rides by ride status
    @GetMapping("/status/{status}")
    public List<RideEntity> getRidesByStatus(@PathVariable("status") String status) {
        return rideRepository.findByRideStatus(status);
    }

    // Add a new ride
    @PostMapping
    public ResponseEntity<RideEntity> addRide(@RequestBody RideEntity rideEntity) {
        // Validate that essential fields like startingPoint, destination, fuelPrice, and carType are provided
        if (rideEntity.getStartingPoint() == null || rideEntity.getDestination() == null || 
            rideEntity.getAvailableSeats() <= 0 || rideEntity.getPricePerHead() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Save the new ride
        RideEntity savedRide = rideRepository.save(rideEntity);
        return ResponseEntity.ok(savedRide);
    }
    
	 // Update an existing ride
	    @PatchMapping("/{id}")
	    public ResponseEntity<RideEntity> updateRide(@PathVariable Long id, @RequestBody RideEntity updatedRide) {
	        try {
	            RideEntity updatedEntity = rideService.updateRide(id, updatedRide);
	            return ResponseEntity.ok(updatedEntity);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.notFound().build();
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body(null);
	        }
	    }
    
    // Search for available rides by starting point, destination, and date
    @GetMapping("/search")
    public List<RideEntity> searchRides(@RequestParam String startingPoint, 
                                        @RequestParam String destination, 
                                        @RequestParam LocalDate date) {
        // Call the repository to find rides that match the criteria
        return rideRepository.findByStartingPointAndDestinationAndDate(startingPoint, destination, date);
    }
}
