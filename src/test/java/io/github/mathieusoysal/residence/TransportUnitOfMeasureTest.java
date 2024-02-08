package io.github.mathieusoysal.residence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.github.mathieusoysal.residence.TransportUnitOfMeasure;

class TransportUnitOfMeasureTest {

    @ParameterizedTest
    @ValueSource(strings = {
            " ",
            "",
            "null"
    })
    void testFromString(String input) {
        // Act
        TransportUnitOfMeasure result = TransportUnitOfMeasure.fromString(input);

        // Assert
        Assertions.assertEquals(TransportUnitOfMeasure.NONE, result);
    }

    @Test
    void testFromString_withMetre_returnsMetre() {
        // Arrange
        String transportUnitOfMeasure = "metre";

        // Act
        TransportUnitOfMeasure result = TransportUnitOfMeasure.fromString(transportUnitOfMeasure);

        // Assert
        Assertions.assertEquals(TransportUnitOfMeasure.METRE, result);
    }

    @Test
    void testFromString_withOnFoot_returnsOnFoot() {
        // Arrange
        String transportUnitOfMeasure = "on_foot";

        // Act
        TransportUnitOfMeasure result = TransportUnitOfMeasure.fromString(transportUnitOfMeasure);

        // Assert
        Assertions.assertEquals(TransportUnitOfMeasure.ON_FOOT, result);
    }

    @Test
    void testFromString_withUnknown_returnsUnknown() {
        // Arrange
        String transportUnitOfMeasure = "unknown";

        // Act
        TransportUnitOfMeasure result = TransportUnitOfMeasure.fromString(transportUnitOfMeasure);

        // Assert
        Assertions.assertEquals(TransportUnitOfMeasure.UNKNOWN, result);
    }
}