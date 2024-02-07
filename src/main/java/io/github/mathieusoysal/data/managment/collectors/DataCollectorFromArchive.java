package io.github.mathieusoysal.data.managment.collectors;

import java.time.LocalDate;
import java.util.List;

import io.github.mathieusoysal.data.managment.convertors.Convertor;
import io.github.mathieusoysal.logement.Logement;

public class DataCollectorFromArchive {
    private final String archiveUrl;

    public DataCollectorFromArchive(String archiveUrl) {
        this.archiveUrl = archiveUrl;
    }

    public List<Logement> getAllLogements() {
        String json = new RequestorToGetAllLogements().requestWitGet(archiveUrl);
        return Convertor.convertJsonToListOfLogements(json);
    }

    public String getSumUpOfDay(LocalDate day) {
        return new RequestorToGetSumUpOfDay(day).requestWitGet(archiveUrl);
    }

    public Integer[][] getConvertedSumUpOfDay(LocalDate day) {
        return new RequestorToGetSumUpOfDay(day).getSumUpOfDay(archiveUrl);
    }
    
}
