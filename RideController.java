package com.acs560.ShareTaxi.controllers;

import com.acs560.ShareTaxi.entities.RideEntity;
import com.acs560.ShareTaxi.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideController {
	
	  private final RideService rideService;

	    @Autowired
	    public RideController(RideService rideService) {
	        this.rideService = rideService;
	    }

    @Autowired
    private RideRepository rideRepository;

    // Get all rides   
    @GetMapping
    public ResponseEntity<List<Ride>> getAllRides() {
        List<Ride> rides = rideService.getAllRides();
        return ResponseEntity.ok(rides);
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

    // Get rides within a specific time range
    @GetMapping("/time-range")
    public List<RideEntity> getRidesByTimeRange(@RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime) {
        return rideRepository.findByStartTimeBetween(startTime, endTime);
    }

    // Get rides by ride status
    @GetMapping("/status/{status}")
    public List<RideEntity> getRidesByStatus(@PathVariable("status") String status) {
        return rideRepository.findByRideStatus(status);
    }

    // Add a new ride
    @PostMapping
    public ResponseEntity<RideEntity> addRide(@RequestBody RideEntity rideEntity) {
        RideEntity savedRide = rideRepository.save(rideEntity);
        return ResponseEntity.ok(savedRide);
    }
}
