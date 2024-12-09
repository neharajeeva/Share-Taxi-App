package com.acs560.ShareTaxi.controllers;

import com.acs560.ShareTaxi.models.TransportStats;
import com.acs560.ShareTaxi.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping
    public List<TransportStats> getStats() {
        return statsService.getStatsFromCsv();
    }
}
