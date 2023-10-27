package io.github.mathieusoysal.logement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransportKindTest {

    @Test
    void testFromString_withValidInput_returnsEnumValue() {
        // Arrange
        String input = "Bus";

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.BUS, result);
    }

    @Test
    void testFromString_withInvalidInput_throwsIllegalArgumentException() {
        // Arrange
        String input = "InvalidTransportKind";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TransportKind.fromString(input);
        });
    }

    @Test
    void testFromString_withNullInput_returnsNone() {
        // Arrange
        String input = null;

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.NONE, result);
    }

    @Test
    void testFromString_withBlankInput_returnsNone() {
        // Arrange
        String input = "";

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.NONE, result);
    }

    @Test
    void testFromString_withNullString_returnsNone() {
        // Arrange
        String input = "null";

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.NONE, result);
    }
}