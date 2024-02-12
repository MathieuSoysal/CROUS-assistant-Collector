package io.github.mathieusoysal.residence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OccupationModeTest {
    
    @Test
    void testHashCode_withSameOccupationMode_returnsSameHashCode() {
        // Arrange
        OccupationMode occupationMode1 = new OccupationMode(OccupationKind.ALONE, 1110, 250);
        OccupationMode occupationMode2 = new OccupationMode(OccupationKind.ALONE, 100, 200);
        
        // Act
        int hashCode1 = occupationMode1.hashCode();
        int hashCode2 = occupationMode2.hashCode();
        
        // Assert
        assertEquals(hashCode1, hashCode2);
    }

}
