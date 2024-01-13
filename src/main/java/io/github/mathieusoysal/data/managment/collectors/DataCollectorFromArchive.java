package io.github.mathieusoysal.data.managment.collectors;

import java.time.LocalDate;

import io.github.mathieusoysal.logement.Logement;

public class DataCollectorFromArchive {
    private final String archiveUrl;

    public DataCollectorFromArchive(String archiveUrl) {
        this.archiveUrl = archiveUrl;
    }

    public String getSumUpOfDay(LocalDate day) {
        return new RequestorToGetSumUpOfDay(day).requestWitGet(archiveUrl);
    }

    public Logement[][] getSumUpConvertedOfDay(LocalDate day) {
        return new RequestorToGetSumUpOfDay(day).getSumUpOfDay(archiveUrl);
    }
    
}
