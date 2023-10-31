package io.github.mathieusoysal.logement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OccupationKindTest {

    @Test
    void testFromString_withValidInput_returnsEnumValue() {
        // Arrange
        String input = "alone";

        // Act
        OccupationKind result = OccupationKind.fromString(input);

        // Assert
        Assertions.assertEquals(OccupationKind.ALONE, result);
    }

    @Test
    void testFromString_withNullInput_returnsNone() {
        // Arrange
        String input = null;

        // Act
        OccupationKind result = OccupationKind.fromString(input);

        // Assert
        Assertions.assertEquals(OccupationKind.NONE, result);
    }

    @Test
    void testFromString_withBlankInput_returnsNone() {
        // Arrange
        String input = "";

        // Act
        OccupationKind result = OccupationKind.fromString(input);

        // Assert
        Assertions.assertEquals(OccupationKind.NONE, result);
    }

    @Test
    void testFromString_withNullString_returnsNone() {
        // Arrange
        String input = "null";

        // Act
        OccupationKind result = OccupationKind.fromString(input);

        // Assert
        Assertions.assertEquals(OccupationKind.NONE, result);
    }

    @Test
    void testFromString_withInvalidInput_returnUnknown() {
        // Arrange
        String input = "InvalidOccupationKind";

        // Act
        OccupationKind result = OccupationKind.fromString(input);

        // Assert
        Assertions.assertEquals(OccupationKind.UNKNOWN, result);
    }

}