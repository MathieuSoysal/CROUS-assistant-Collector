package io.github.mathieusoysal.logement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransportUnitOfMeasureTest {

    @Test
    void testFromString_withNull_returnsNone() {
        // Arrange
        String transportUnitOfMeasure = null;

        // Act
        TransportUnitOfMeasure result = TransportUnitOfMeasure.fromString(transportUnitOfMeasure);

        // Assert
        Assertions.assertEquals(TransportUnitOfMeasure.NONE, result);
    }

    @Test
    void testFromString_withBlank_returnsNone() {
        // Arrange
        String transportUnitOfMeasure = "";

        // Act
        TransportUnitOfMeasure result = TransportUnitOfMeasure.fromString(transportUnitOfMeasure);

        // Assert
        Assertions.assertEquals(TransportUnitOfMeasure.NONE, result);
    }

    @Test
    void testFromString_withNullString_returnsNone() {
        // Arrange
        String transportUnitOfMeasure = "null";

        // Act
        TransportUnitOfMeasure result = TransportUnitOfMeasure.fromString(transportUnitOfMeasure);

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