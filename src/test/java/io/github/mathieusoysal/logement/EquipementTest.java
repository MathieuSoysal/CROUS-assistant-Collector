package io.github.mathieusoysal.logement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EquipementTest {

    @Test
    void testFromString_withValidInput_returnsEnumValue() {
        // Arrange
        String input = "WC";

        // Act
        Equipement result = Equipement.fromString(input);

        // Assert
        Assertions.assertEquals(Equipement.WC, result);
    }

    @Test
    void testFromString_withNullInput_returnsNone() {
        // Arrange
        String input = null;

        // Act
        Equipement result = Equipement.fromString(input);

        // Assert
        Assertions.assertEquals(Equipement.NONE, result);
    }

    @Test
    void testFromString_withBlankInput_returnsNone() {
        // Arrange
        String input = "";

        // Act
        Equipement result = Equipement.fromString(input);

        // Assert
        Assertions.assertEquals(Equipement.NONE, result);
    }

    @Test
    void testFromString_withNullString_returnsNone() {
        // Arrange
        String input = "null";

        // Act
        Equipement result = Equipement.fromString(input);

        // Assert
        Assertions.assertEquals(Equipement.NONE, result);
    }

    @Test
    void testFromString_withInvalidInput_returnUnknown() {
        // Arrange
        String input = "InvalidEquipement";

        // Act
        Equipement result = Equipement.fromString(input);

        // Assert
        Assertions.assertEquals(Equipement.UNKNOWN, result);
    }
}