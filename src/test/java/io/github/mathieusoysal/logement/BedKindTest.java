package io.github.mathieusoysal.logement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BedKindTest {

    @Test
    void testFromString_withValidInput_returnsEnumValue() {
        // Arrange
        String input = "simple";

        // Act
        BedKind result = BedKind.fromString(input);

        // Assert
        Assertions.assertEquals(BedKind.SIMPLE, result);
    }

    @Test
    void testFromString_withNullInput_returnsNone() {
        // Arrange
        String input = null;

        // Act
        BedKind result = BedKind.fromString(input);

        // Assert
        Assertions.assertEquals(BedKind.NONE, result);
    }

    @Test
    void testFromString_withBlankInput_returnsNone() {
        // Arrange
        String input = "";

        // Act
        BedKind result = BedKind.fromString(input);

        // Assert
        Assertions.assertEquals(BedKind.NONE, result);
    }

    @Test
    void testFromString_withNullString_returnsNone() {
        // Arrange
        String input = "null";

        // Act
        BedKind result = BedKind.fromString(input);

        // Assert
        Assertions.assertEquals(BedKind.NONE, result);
    }

    @Test
    void testFromString_withInvalidInput_returnUnknown() {
        // Arrange
        String input = "InvalidBedKind";

        // Act
        BedKind result = BedKind.fromString(input);

        // Assert
        Assertions.assertEquals(BedKind.UNKNOWN, result);
    }

}