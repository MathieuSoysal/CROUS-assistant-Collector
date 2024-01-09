package io.github.mathieusoysal.data.managment.collectors;

import java.time.LocalDate;

public class DataCollectorFromArchive {
    private final String archiveUrl;

    public DataCollectorFromArchive(String archiveUrl) {
        this.archiveUrl = archiveUrl;
    }

    public String getSumUpOfDay(LocalDate day) {
        return new RequestorToGetSumUpOfDay(day).requestWitGet(archiveUrl);
    }
    
}
