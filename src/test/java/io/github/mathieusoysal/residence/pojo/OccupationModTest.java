package io.github.mathieusoysal.residence.pojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.residence.OccupationKind;
import io.github.mathieusoysal.residence.OccupationMode;

class OccupationModTest {
    @Test
    void testGetOccupationKind_returnsCorrectValue() {
        // Arrange
        OccupationKind expectedOccupationKind = OccupationKind.ALONE;
        int rentMin = 100;
        int rentMax = 200;
        OccupationMode occupationMod = new OccupationMode(expectedOccupationKind, rentMin, rentMax);

        // Act
        OccupationKind actualOccupationKind = occupationMod.getOccupationKind();

        // Assert
        Assertions.assertEquals(expectedOccupationKind, actualOccupationKind);
    }

    @Test
    void testGetRentMin_returnsCorrectValue() {
        // Arrange
        OccupationKind occupationKind = OccupationKind.ALONE;
        int expectedRentMin = 100;
        int rentMax = 200;
        OccupationMode occupationMod = new OccupationMode(occupationKind, expectedRentMin, rentMax);

        // Act
        int actualRentMin = occupationMod.getRentMin();

        // Assert
        Assertions.assertEquals(expectedRentMin, actualRentMin);
    }

    @Test
    void testGetRentMax_returnsCorrectValue() {
        // Arrange
        OccupationKind occupationKind = OccupationKind.ALONE;
        int rentMin = 100;
        int expectedRentMax = 200;
        OccupationMode occupationMod = new OccupationMode(occupationKind, rentMin, expectedRentMax);

        // Act
        int actualRentMax = occupationMod.getRentMax();

        // Assert
        Assertions.assertEquals(expectedRentMax, actualRentMax);
    }
}