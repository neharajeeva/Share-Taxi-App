package com.acs560.ShareTaxi.services;

import com.acs560.ShareTaxi.models.TransportStats;
import java.util.List;

public interface StatsService {
    List<TransportStats> getStatsFromCsv();
}
