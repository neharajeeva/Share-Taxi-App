package com.acs560.ShareTaxi.models;

import lombok.Getter;
import lombok.Setter;

/**
 * The RideRequest class represents a request made by a user to join a specific ride
 * in the ShareTaxi application. It contains details about the ride being requested,
 * the user making the request, the status of the request, the number of seats requested,
 * and any additional comments from the user.
 * This class is primarily used for data transfer between different layers
 * of the application and is not mapped directly to the database.
 */
@Getter
@Setter
public class RideRequest {

    /**
     * Unique identifier for the ride request.
     */
    private Long id;

    /**
     * Identifier for the ride being requested.
     */
    private Long rideId;

    /**
     * Identifier for the user making the ride request.
     */
    private Long requestedById;

    /**
     * The current status of the request (e.g., "Pending", "Approved", "Rejected").
     */
    private String requestStatus;

    /**
     * The number of seats requested by the user.
     * Defaults to 1 if not specified.
     */
    private int seatsRequested = 1;

    /**
     * Any additional comments provided by the user when requesting the ride.
     */
    private String comments;
}