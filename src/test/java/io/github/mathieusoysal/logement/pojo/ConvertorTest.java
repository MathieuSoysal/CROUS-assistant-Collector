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
import io.github.mathieusoysal.logement.TransportKind;
import io.github.mathieusoysal.logement.TransportUnitOfMeasure;

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
                logement.getAddress().getFullAddress());
        assertEquals(50.331, logement.getAddress().getLocation().latitude());
        assertEquals(3.515, logement.getAddress().getLocation().longitude());
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
        assertEquals(2, logement.getTransports().size());
        assertEquals(TransportKind.TRAMWAY, logement.getTransports().get(0).getKind());
        assertEquals(TransportUnitOfMeasure.ON_FOOT, logement.getTransports().get(0).getUnitOfMeasure());
        assertEquals(1, logement.getTransports().get(0).getDistance());
        assertEquals("Station Chemin Vert", logement.getTransports().get(0).getDescription());
        assertEquals(TransportKind.BUS, logement.getTransports().get(1).getKind());
        assertEquals(TransportUnitOfMeasure.ON_FOOT, logement.getTransports().get(1).getUnitOfMeasure());
        assertEquals(2, logement.getTransports().get(1).getDistance());
        assertEquals("Arrêt Chemin vert", logement.getTransports().get(1).getDescription());
        assertEquals(12.5, logement.getAreaMin());
        assertEquals(12.5, logement.getAreaMax());
        assertEquals(2, logement.getEquipements().size());
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