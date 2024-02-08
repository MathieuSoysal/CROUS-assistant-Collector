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

import io.github.mathieusoysal.exceptions.ApiRequestFailedException;
import io.github.mathieusoysal.logement.Logement;

class DataCollectorTest {

    @Test
    void testGetLogementsWithoutConnection_returnsEmptyList()
            throws StreamReadException, DatabindException, ApiRequestFailedException, IOException {
        // Arrange

        // Act
        List<Logement> result = DataCollectorFromCrous.getAvailableLogementsWithoutConnection();

        // Assert
        Assertions.assertNotEquals(0, result.size());
    }

    @Test
    void testGetAllLogementsWithoutConnection_returnsLogements()
            throws StreamReadException, DatabindException, ApiRequestFailedException, IOException {
        // Arrange

        // Act
        List<Logement> result = DataCollectorFromCrous.getAllLogementsWithoutConnection();

        // Assert
        Assertions.assertNotEquals(0, result.size());
    }

    @Test
    void testConvertion() throws StreamReadException, DatabindException, ApiRequestFailedException, IOException {
        List<Logement> result = DataCollectorFromCrous.getAllLogementsWithoutConnection();

        assertNotNull(result);

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(result);

        assertNotNull(json);


        assertDoesNotThrow(() -> objectMapper.readValue(json,
                new com.fasterxml.jackson.core.type.TypeReference<List<Logement>>() {
                }));
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "CI", matches = "true")
    void testGetLogementsWithConnection_returnsLogements()
            throws StreamReadException, DatabindException, ApiRequestFailedException, IOException,
            InterruptedException {
        // Arrange
        String email = System.getenv("TEST_MAIL");
        String password = System.getenv("TEST_PASSWORD");

        // Act
        assertNotNull(email, "Please set TEST_MAIL environment variable");
        assertNotNull(password, "Please set TEST_PASSWORD environment variable");
        List<Logement> result = DataCollectorFromCrous.getAvailableLogementsWithConnection(
                email,
                password);

        // Assert
        Assertions.assertNotEquals(DataCollectorFromCrous.getAvailableLogementsWithoutConnection().size(),
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
    void testGetAllLogements()
    {
        String linkToData = "https://mathieusoysal.github.io/CROUS-assistant-Collector/v2";
        assertDoesNotThrow(() -> new DataCollectorFromArchive(linkToData).getAllLogements());
    }

}