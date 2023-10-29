package io.github.mathieusoysal.logement.data;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import io.github.mathieusoysal.exceptions.ApiRequestFailedException;
import io.github.mathieusoysal.logement.pojo.Logement;

class DataCollectorTest {

    @Test
    void testGetLogementsWithoutConnection_returnsEmptyList()
            throws StreamReadException, DatabindException, ApiRequestFailedException, IOException {
        // Arrange

        // Act
        List<Logement> result = DataCollector.getAvailableLogementsWithoutConnection();

        // Assert
        Assertions.assertNotEquals(0, result.size());
    }

}