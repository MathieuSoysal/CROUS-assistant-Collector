package io.github.mathieusoysal.logement.pojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.logement.OccupationKind;
import io.github.mathieusoysal.logement.pojo.OccupationMod;

class OccupationModTest {
    @Test
    void testGetOccupationKind_returnsCorrectValue() {
        // Arrange
        OccupationKind expectedOccupationKind = OccupationKind.ALONE;
        int rentMin = 100;
        int rentMax = 200;
        OccupationMod occupationMod = new OccupationMod(expectedOccupationKind, rentMin, rentMax);

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
        OccupationMod occupationMod = new OccupationMod(occupationKind, expectedRentMin, rentMax);

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
        OccupationMod occupationMod = new OccupationMod(occupationKind, rentMin, expectedRentMax);

        // Act
        int actualRentMax = occupationMod.getRentMax();

        // Assert
        Assertions.assertEquals(expectedRentMax, actualRentMax);
    }
}