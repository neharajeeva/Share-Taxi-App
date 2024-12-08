package com.acs560.ShareTaxi.services;

import com.acs560.ShareTaxi.entities.RideRequestEntity;

import java.util.List;

public interface RideRequestService {

    RideRequestEntity createRideRequest(RideRequestEntity rideRequest);

    RideRequestEntity updateRideRequest(Long requestId, RideRequestEntity rideRequest);

    void deleteRideRequest(Long requestId);

    RideRequestEntity getRideRequestById(Long requestId);

    List<RideRequestEntity> getRideRequestsByRide(Long rideId);

    List<RideRequestEntity> getRideRequestsByUser(Long userId);
    
    List<RideRequestEntity> getAllRideRequests();
}
