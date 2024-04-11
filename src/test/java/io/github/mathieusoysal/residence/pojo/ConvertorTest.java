package io.github.mathieusoysal.residence.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.residence.BedKind;
import io.github.mathieusoysal.residence.OccupationKind;
import io.github.mathieusoysal.residence.Residence;
import io.github.mathieusoysal.residence.TransportKind;
import io.github.mathieusoysal.residence.TransportUnitOfMeasure;

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
        Assertions.assertEquals(109, items.get(0).getId());
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
    void testConvertItemsToResidences() throws IOException {
        // Act
        List<Residence> residences = Convertor.getResidencesFromBruteJsonFile(jsonTestFile);

        // Assert
        assertEquals(1, residences.size());
        Residence residence = residences.get(0);
        assertEquals(109, residence.getId());
        assertEquals("Cité Jules Mousseron", residence.getLabel());
        assertEquals("Rue du Chemin Vert 59300 AULNOY-LES-VALENCIENNES",
                residence.getAddress().getFullAddress());
        assertEquals(50.331, residence.getAddress().getLocation().latitude());
        assertEquals(3.515, residence.getAddress().getLocation().longitude());
        assertEquals(1, residence.getBedCount());
        assertEquals(BedKind.SIMPLE, residence.getBedKind());
        assertEquals(1, residence.getBedroomCount());
        assertEquals(1, residence.getRoomCount());
        assertFalse(residence.isInUnavailabilityPeriod());
        assertEquals("<p>Chambre disposant de sanitaires privatifs (douche, WC, lavabo). Cuisine collecitve</p>",
                residence.getDescription());
        assertTrue(residence.isAvailable());
        assertFalse(residence.isHighDemand());
        assertFalse(residence.isLowStock());
        assertEquals(2, residence.getTransports().size());
        assertEquals(TransportKind.TRAMWAY, residence.getTransports().get(0).getKind());
        assertEquals(TransportUnitOfMeasure.ON_FOOT, residence.getTransports().get(0).getUnitOfMeasure());
        assertEquals(1, residence.getTransports().get(0).getDistance());
        assertEquals("Station Chemin Vert", residence.getTransports().get(0).getDescription());
        assertEquals(TransportKind.BUS, residence.getTransports().get(1).getKind());
        assertEquals(TransportUnitOfMeasure.ON_FOOT, residence.getTransports().get(1).getUnitOfMeasure());
        assertEquals(2, residence.getTransports().get(1).getDistance());
        assertEquals("Arrêt Chemin vert", residence.getTransports().get(1).getDescription());
        assertEquals(12.5, residence.getAreaMin());
        assertEquals(12.5, residence.getAreaMax());
        assertEquals(2, residence.getEquipements().size());
        assertEquals(io.github.mathieusoysal.residence.Equipment.WC,
                residence.getEquipements().get(0));
        assertEquals(io.github.mathieusoysal.residence.Equipment.SHOWER,
                residence.getEquipements().get(1));
        assertEquals(1, residence.getOccupationMods().size());
        assertEquals(OccupationKind.ALONE, residence.getOccupationMods().get(0).getOccupationKind());
        assertEquals(26780, residence.getOccupationMods().get(0).getRentMin());
        assertEquals(26780, residence.getOccupationMods().get(0).getRentMax());
    }

}