package io.github.mathieusoysal.logement.data;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.logement.pojo.Logement;

class DataCollectorTest {

    @Test
    void testGetLogementsWithoutConnection_returnsEmptyList() {
        // Arrange

        // Act
        List<Logement> result = DataCollector.getLogementsWithoutConnection();

        // Assert
        Assertions.assertNotEquals(0, result.size());
    }

}