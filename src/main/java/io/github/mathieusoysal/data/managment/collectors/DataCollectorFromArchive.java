package io.github.mathieusoysal.data.managment.collectors;

import java.time.LocalDate;
import java.util.List;

import io.github.mathieusoysal.data.managment.collectors.requestors.RequestorToGetAllResidences;
import io.github.mathieusoysal.data.managment.collectors.requestors.RequestorToGetSumUpOfDay;
import io.github.mathieusoysal.data.managment.convertors.Convertor;
import io.github.mathieusoysal.residence.Residence;

public class DataCollectorFromArchive {
    private final String archiveUrl;

    public DataCollectorFromArchive(String archiveUrl) {
        this.archiveUrl = archiveUrl;
    }

    public List<Residence> getAllResidences() {
        String json = new RequestorToGetAllResidences().requestWitGet(archiveUrl);
        return Convertor.convertJsonToListOfResidences(json);
    }

    public String getSumUpOfDay(LocalDate day) {
        return new RequestorToGetSumUpOfDay(day).requestWitGet(archiveUrl);
    }

    public Integer[][] getConvertedSumUpOfDay(LocalDate day) {
        return new RequestorToGetSumUpOfDay(day).getSumUpOfDay(archiveUrl);
    }
}
