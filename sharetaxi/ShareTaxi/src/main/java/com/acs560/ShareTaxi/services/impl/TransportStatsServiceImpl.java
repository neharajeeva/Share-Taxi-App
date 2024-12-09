package com.acs560.ShareTaxi.services.impl;

import com.acs560.ShareTaxi.models.TransportStats;
import com.acs560.ShareTaxi.services.StatsService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {

    private static final String CSV_FILE_PATH = "student_transport_stats.csv"; // Relative to resources folder

    @Override
    public List<TransportStats> getStatsFromCsv() {
        List<TransportStats> stats = new ArrayList<>();

        // Load the file as a resource
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CSV_FILE_PATH);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                throw new IllegalArgumentException("CSV file not found in resources folder");
            }

            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                stats.add(new TransportStats(data[0], Integer.parseInt(data[1])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stats;
    }
}
