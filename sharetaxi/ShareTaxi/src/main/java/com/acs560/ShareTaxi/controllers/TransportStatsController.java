package com.acs560.ShareTaxi.controllers;

import com.acs560.ShareTaxi.entities.TransportStatsEntity;
import com.acs560.ShareTaxi.models.TransportStats;
import com.acs560.ShareTaxi.services.TransportStatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class TransportStatsController {

    @Autowired
    private TransportStatsService transportStatsService;

    @GetMapping
    public List<TransportStatsEntity> getStats() {
        return transportStatsService.getStatsFromCsv();
    }
}
