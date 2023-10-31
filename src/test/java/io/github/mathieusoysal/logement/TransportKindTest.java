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

    @Test
    void testFromString_withValidInput_returnsPayingUrbanParking() {
        // Arrange
        String input = "Stationnement urbain payant";

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.PAYING_URBAN_PARKING, result);
    }

    @Test
    void testFromString_withValidInput_returnsFreeUrbanParking() {
        // Arrange
        String input = "Stationnement urbain gratuit";

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.FREE_URBAN_PARKING, result);
    }

    @Test
    void testFromString_withValidInput_returnsTramway() {
        // Arrange
        String input = "Tramway";

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.TRAMWAY, result);
    }

    @Test
    void testFromString_withValidInput_returnsPrivateParking() {
        // Arrange
        String input = "Parking privatif";

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.PRIVATE_PARKING, result);
    }

    @Test
    void testFromString_withValidInput_returnsTrain() {
        // Arrange
        String input = "Train";

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.TRAIN, result);
    }

    @Test
    void testFromString_withValidInput_returnsBike() {
        // Arrange
        String input = "Vélo";

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.BIKE, result);
    }

    @Test
    void testFromString_withValidInput_returnsRer() {
        // Arrange
        String input = "RER";

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.RER, result);
    }

    @Test
    void testFromString_withValidInput_returnsMetro() {
        // Arrange
        String input = "Métro";

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.METRO, result);
    }

    @Test
    void testFromString_withEmptyInput_returnsNone() {
        // Arrange
        String input = "";

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.NONE, result);
    }

    @Test
    void testFromString_withNullStringInput_returnsNone() {
        // Arrange
        String input = "null";

        // Act
        TransportKind result = TransportKind.fromString(input);

        // Assert
        Assertions.assertEquals(TransportKind.NONE, result);
    }
}