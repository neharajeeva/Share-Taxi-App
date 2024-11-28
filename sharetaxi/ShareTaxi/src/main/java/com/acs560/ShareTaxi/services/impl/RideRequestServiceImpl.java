package com.acs560.ShareTaxi.services.impl;

import com.acs560.ShareTaxi.entities.RideRequestEntity;
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
}
