package com.acs560.ShareTaxi.repositories;

import com.acs560.ShareTaxi.entities.TransportStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportStatsRepository extends JpaRepository<TransportStatsEntity, Long> {}
