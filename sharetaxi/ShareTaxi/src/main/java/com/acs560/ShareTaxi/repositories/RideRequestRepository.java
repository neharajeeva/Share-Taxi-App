package com.acs560.ShareTaxi.repositories;

import com.acs560.ShareTaxi.entities.RideRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequestEntity, Long> {

    List<RideRequestEntity> findByRide_Id(Long rideId);

    List<RideRequestEntity> findByRequestedBy_Id(Long userId);
}
