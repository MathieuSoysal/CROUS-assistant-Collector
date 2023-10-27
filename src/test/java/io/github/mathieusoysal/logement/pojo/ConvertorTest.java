package io.github.mathieusoysal.logement.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.logement.BedKind;
import io.github.mathieusoysal.logement.OccupationKind;

class ConvertorTest {
    private static File jsonTestFile = new File("src/test/java/io/github/mathieusoysal/resources/test.json");

    @Test
    void testGetItemsFromJsonFile_isNotEmpty() throws IOException {
        // Act
        List<Item> items = Convertor.getItemsFromJsonFile(jsonTestFile);

        // Assert
        Assertions.assertEquals(1, items.size());
    }

    @Test
    void testGetItemsFromJsonFile_containTheGoodId() throws IOException {
        // Act
        List<Item> items = Convertor.getItemsFromJsonFile(jsonTestFile);

        // Assert
        Assertions.assertEquals(227, items.get(0).getId());
    }

    @Test
    void testGetItemsFromJsonFile_containTheGoodBedCount() throws IOException {
        // Act
        List<Item> items = Convertor.getItemsFromJsonFile(jsonTestFile);

        // Assert
        Assertions.assertEquals(1, items.get(0).getBedCount());
    }

    @Test
    void testGetItemsFromJsonFile_containTheGoodBedroomCount() throws IOException {
        // Act
        List<Item> items = Convertor.getItemsFromJsonFile(jsonTestFile);

        // Assert
        Assertions.assertEquals(1, items.get(0).getBedroomCount());
    }

    @Test
    void testConvertItemsToLogements() throws IOException {
        // Act
        List<Logement> logements = Convertor.getLogementsFromJsonFile(jsonTestFile);

        // Assert
        assertEquals(1, logements.size());
        Logement logement = logements.get(0);
        assertEquals(227, logement.getId());
        assertEquals("Cité Jules Mousseron", logement.getLabel());
        assertEquals("Rue du Chemin Vert 59300 AULNOY-LES-VALENCIENNES",
                logement.getAddress().toString());
        assertEquals(1, logement.getBedCount());
        assertEquals(BedKind.SIMPLE, logement.getBedKind());
        assertEquals(1, logement.getBedroomCount());
        assertEquals(1, logement.getRoomCount());
        assertFalse(logement.isInUnavailabilityPeriod());
        assertEquals("<p>Chambre disposant de sanitaires privatifs (douche, WC, lavabo). Cuisine collecitve</p>",
                logement.getDescription());
        assertTrue(logement.isAvailable());
        assertFalse(logement.isHighDemand());
        assertFalse(logement.isLowStock());
        assertEquals(12.5, logement.getAreaMin());
        assertEquals(12.5, logement.getAreaMax());
        assertEquals(1, logement.getEquipements().size());
        assertEquals(io.github.mathieusoysal.logement.Equipement.WC,
                logement.getEquipements().get(0));
        assertEquals(io.github.mathieusoysal.logement.Equipement.SHOWER,
                logement.getEquipements().get(1));
        assertEquals(1, logement.getOccupationMods().size());
        assertEquals(OccupationKind.ALONE, logement.getOccupationMods().get(0).getOccupationKind());
        assertEquals(26780, logement.getOccupationMods().get(0).getRentMin());
        assertEquals(26780, logement.getOccupationMods().get(0).getRentMax());
    }

}