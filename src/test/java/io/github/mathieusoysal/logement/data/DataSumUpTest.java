package io.github.mathieusoysal.logement.data;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class DataSumUpTest {

    @Test
    void testCreateSumUpOfTheDay() throws JsonProcessingException {
        String linkToData = "https://mathieusoysal.github.io/CROUS-assistant-Collector/v1/logements-crous/available/";
        LocalDate date = LocalDate.parse("2024-01-02", DateTimeFormatter.ISO_LOCAL_DATE);
        assertDoesNotThrow(
                () -> DataSumUp.createSumUpOfTheDay(date, linkToData));
    }

}