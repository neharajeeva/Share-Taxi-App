package com.acs560.ShareTaxi.controllers;

import com.acs560.ShareTaxi.entities.RideRequestEntity;
import com.acs560.ShareTaxi.services.RideRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ride-requests")
public class RideRequestController {

    @Autowired
    private RideRequestService rideRequestService;

    @PostMapping
    public ResponseEntity<RideRequestEntity> createRideRequest(@RequestBody RideRequestEntity rideRequest) {
        RideRequestEntity createdRequest = rideRequestService.createRideRequest(rideRequest);
        return ResponseEntity.ok(createdRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RideRequestEntity> updateRideRequest(
            @PathVariable Long id, 
            @RequestBody RideRequestEntity updatedRequest) {
        RideRequestEntity updatedRideRequest = rideRequestService.updateRideRequest(id, updatedRequest);
        return ResponseEntity.ok(updatedRideRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRideRequest(@PathVariable Long id) {
        rideRequestService.deleteRideRequest(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RideRequestEntity> getRideRequestById(@PathVariable Long id) {
        RideRequestEntity rideRequest = rideRequestService.getRideRequestById(id);
        return ResponseEntity.ok(rideRequest);
    }

    @GetMapping("/by-ride/{rideId}")
    public ResponseEntity<List<RideRequestEntity>> getRideRequestsByRide(@PathVariable Long rideId) {
        List<RideRequestEntity> rideRequests = rideRequestService.getRideRequestsByRide(rideId);
        return ResponseEntity.ok(rideRequests);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<RideRequestEntity>> getRideRequestsByUser(@PathVariable Long userId) {
        List<RideRequestEntity> rideRequests = rideRequestService.getRideRequestsByUser(userId);
        return ResponseEntity.ok(rideRequests);
    }
    
    // New endpoint to fetch all ride requests
    @GetMapping
    public ResponseEntity<List<RideRequestEntity>> getAllRideRequests() {
        List<RideRequestEntity> allRideRequests = rideRequestService.getAllRideRequests();
        return ResponseEntity.ok(allRideRequests);
    }
}
