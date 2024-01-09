package io.github.mathieusoysal.logement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EquipementTest {

    @Test
    void testFromString_withValidInput_returnsEnumValue() {
        // Arrange
        String input = "WC";

        // Act
        Equipment result = Equipment.fromString(input);

        // Assert
        Assertions.assertEquals(Equipment.WC, result);
    }

    @Test
    void testFromString_withNullInput_returnsNone() {
        // Arrange
        String input = null;

        // Act
        Equipment result = Equipment.fromString(input);

        // Assert
        Assertions.assertEquals(Equipment.NONE, result);
    }

    @Test
    void testFromString_withBlankInput_returnsNone() {
        // Arrange
        String input = "";

        // Act
        Equipment result = Equipment.fromString(input);

        // Assert
        Assertions.assertEquals(Equipment.NONE, result);
    }

    @Test
    void testFromString_withNullString_returnsNone() {
        // Arrange
        String input = "null";

        // Act
        Equipment result = Equipment.fromString(input);

        // Assert
        Assertions.assertEquals(Equipment.NONE, result);
    }

    @Test
    void testFromString_withInvalidInput_returnUnknown() {
        // Arrange
        String input = "InvalidEquipement";

        // Act
        Equipment result = Equipment.fromString(input);

        // Assert
        Assertions.assertEquals(Equipment.UNKNOWN, result);
    }
}