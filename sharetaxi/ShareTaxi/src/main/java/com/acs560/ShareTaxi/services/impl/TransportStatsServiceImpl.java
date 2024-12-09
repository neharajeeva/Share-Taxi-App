package com.acs560.ShareTaxi.services.impl;

import com.acs560.ShareTaxi.entities.TransportStatsEntity;
import com.acs560.ShareTaxi.repositories.TransportStatsRepository;
import com.acs560.ShareTaxi.services.TransportStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransportStatsServiceImpl implements TransportStatsService {

    private static final String CSV_FILE_PATH = "student_transport_stats.csv"; // Relative to resources folder

    @Autowired
    private TransportStatsRepository transportStatsRepository;

    @Override
    public List<TransportStatsEntity> getStatsFromCsv() {
        List<TransportStatsEntity> stats = new ArrayList<>();

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
                TransportStatsEntity stat = new TransportStatsEntity(data[0], Integer.parseInt(data[1]));
                stats.add(stat);
            }

            // Save all to database
            transportStatsRepository.saveAll(stats);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stats;
    }
}
