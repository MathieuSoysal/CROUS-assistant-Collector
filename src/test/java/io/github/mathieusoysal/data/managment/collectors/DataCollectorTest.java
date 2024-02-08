package io.github.mathieusoysal.data.managment.collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.mathieusoysal.exceptions.requests.ApiRequestFailedException;
import io.github.mathieusoysal.residence.Residence;

class DataCollectorTest {

    @Test
    void testGetResidencesWithoutConnection_returnsEmptyList()
            throws StreamReadException, DatabindException, ApiRequestFailedException, IOException {
        // Arrange

        // Act
        List<Residence> result = DataCollectorFromCrous.getAvailableResidencesWithoutConnection();

        // Assert
        Assertions.assertNotEquals(0, result.size());
    }

    @Test
    void testGetAllResidencesWithoutConnection_returnsResidences()
            throws StreamReadException, DatabindException, ApiRequestFailedException, IOException {
        // Arrange

        // Act
        List<Residence> result = DataCollectorFromCrous.getAllResidencesWithoutConnection();

        // Assert
        Assertions.assertNotEquals(0, result.size());
    }

    @Test
    void testConvertion() throws StreamReadException, DatabindException, ApiRequestFailedException, IOException {
        List<Residence> result = DataCollectorFromCrous.getAllResidencesWithoutConnection();

        assertNotNull(result);

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(result);

        assertNotNull(json);


        assertDoesNotThrow(() -> objectMapper.readValue(json,
                new com.fasterxml.jackson.core.type.TypeReference<List<Residence>>() {
                }));
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "CI", matches = "true")
    void testGetResidencesWithConnection_returnsResidences()
            throws StreamReadException, DatabindException, ApiRequestFailedException, IOException,
            InterruptedException {
        // Arrange
        String email = System.getenv("TEST_MAIL");
        String password = System.getenv("TEST_PASSWORD");

        // Act
        assertNotNull(email, "Please set TEST_MAIL environment variable");
        assertNotNull(password, "Please set TEST_PASSWORD environment variable");
        List<Residence> result = DataCollectorFromCrous.getAvailableResidencesWithConnection(
                email,
                password);

        // Assert
        Assertions.assertNotEquals(DataCollectorFromCrous.getAvailableResidencesWithoutConnection().size(),
                result.size());
    }

    @Test
    void testCreateSumUpOfTheDay() throws JsonProcessingException {
        String linkToData = "https://mathieusoysal.github.io/CROUS-assistant-Collector/v2";
        LocalDate date = LocalDate.parse("2024-01-02", DateTimeFormatter.ISO_LOCAL_DATE);
        assertDoesNotThrow(
                () -> {
                    DataCollectorFromArchive dataCollectorFromArchive = new DataCollectorFromArchive(linkToData);
                    dataCollectorFromArchive.getSumUpOfDay(date);});
    }

    @Test
    void testGetAllResidences()
    {
        String linkToData = "https://mathieusoysal.github.io/CROUS-assistant-Collector/v2";
        assertDoesNotThrow(() -> new DataCollectorFromArchive(linkToData).getAllResidences());
    }

}