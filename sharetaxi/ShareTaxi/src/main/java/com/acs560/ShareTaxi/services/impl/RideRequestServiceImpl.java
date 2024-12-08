package com.acs560.ShareTaxi.services.impl;

import com.acs560.ShareTaxi.entities.CustomUserEntity;
import com.acs560.ShareTaxi.entities.RideEntity;
import com.acs560.ShareTaxi.entities.RideRequestEntity;
import com.acs560.ShareTaxi.repositories.CustomUserRepository;
import com.acs560.ShareTaxi.repositories.RideRepository;
import com.acs560.ShareTaxi.repositories.RideRequestRepository;
import com.acs560.ShareTaxi.services.RideRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RideRequestServiceImpl implements RideRequestService {

    @Autowired
    private RideRequestRepository rideRequestRepository;
    
    @Autowired
    private RideRepository rideRepository;
    
    @Autowired
    private CustomUserRepository customUserRepository;

    @Override
    public RideRequestEntity createRideRequest(RideRequestEntity rideRequest) {
        return rideRequestRepository.save(rideRequest);
    }

    @Override
    public RideRequestEntity updateRideRequest(Long requestId, RideRequestEntity updatedRequest) {
        Optional<RideRequestEntity> existingRequest = rideRequestRepository.findById(requestId);
        if (existingRequest.isPresent()) {
            RideRequestEntity request = existingRequest.get();
            request.setRequestStatus(updatedRequest.getRequestStatus());
            request.setSeatsRequested(updatedRequest.getSeatsRequested());
            request.setComments(updatedRequest.getComments());
            
            // Check if the request is accepted
            if ("Accepted".equalsIgnoreCase(updatedRequest.getRequestStatus())) {
                Optional<RideEntity> rideOptional = rideRepository.findById(request.getRide().getId());
                Optional<CustomUserEntity> userOptional = customUserRepository.findById(request.getRequestedBy().getId());

                if (rideOptional.isPresent() && userOptional.isPresent()) {
                    RideEntity ride = rideOptional.get();
                    CustomUserEntity user = userOptional.get();

                    // Add user to the ride's passenger list
                    ride.getPassengers().add(user);

                    // Save the ride to update the passengers list
                    rideRepository.save(ride);
                } else {
                    throw new IllegalArgumentException("Ride or User not found for the given request.");
                }
            }
            return rideRequestRepository.save(request);
        }
        throw new IllegalArgumentException("Ride request with ID " + requestId + " not found.");
    }

    @Override
    public void deleteRideRequest(Long requestId) {
        rideRequestRepository.deleteById(requestId);
    }

    @Override
    public RideRequestEntity getRideRequestById(Long requestId) {
        return rideRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Ride request with ID " + requestId + " not found."));
    }

    @Override
    public List<RideRequestEntity> getRideRequestsByRide(Long rideId) {
        return rideRequestRepository.findByRide_Id(rideId);
    }

    @Override
    public List<RideRequestEntity> getRideRequestsByUser(Long userId) {
        return rideRequestRepository.findByRequestedBy_Id(userId);
    }
    
    @Override
    public List<RideRequestEntity> getAllRideRequests() {
        return rideRequestRepository.findAll();
    }
    
}
