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
        assertEquals("Résidence Le Fenouillet", residence.getLabel());
        assertEquals("CROUS NICE TOULON - Site du Var CS80577 - 83041 TOULON CEDEX 9",
                residence.getAddress().getFullAddress());
        assertEquals(43.134, residence.getAddress().getLocation().latitude());
        assertEquals(6.016, residence.getAddress().getLocation().longitude());
        assertEquals(1, residence.getBedCount());
        assertEquals(BedKind.SIMPLE, residence.getBedKind());
        assertEquals(1, residence.getBedroomCount());
        assertEquals(1, residence.getRoomCount());
        assertFalse(residence.isInUnavailabilityPeriod());
        assertEquals("<p>Type de logement : T1 de 16 m2 - 1 pers &Eacute;quipement : kitchenette, &eacute;vier, r&eacute;frig&eacute;rateur | Sanitaires : lavabo, douche, WC | Mobilier : lit une place, bureau, chaise ou tabouret, placard, possibilit&eacute; de location d&rsquo;un kit linge (draps et couvertures) | Installations : acc&egrave;s internet wifi inclus</p>",
                residence.getDescription());
        assertTrue(residence.isAvailable());
        assertFalse(residence.isHighDemand());
        assertFalse(residence.isLowStock());
        assertEquals(1, residence.getTransports().size());
        assertEquals(TransportKind.BUS, residence.getTransports().get(0).getKind());
        assertEquals(TransportUnitOfMeasure.ON_FOOT, residence.getTransports().get(0).getUnitOfMeasure());
        assertEquals(10, residence.getTransports().get(0).getDistance());
        assertEquals("dessertes régulières de bus vers Toulon", residence.getTransports().get(0).getDescription());
        assertEquals(16, residence.getAreaMin());
        assertEquals(16, residence.getAreaMax());
        assertEquals(4, residence.getEquipements().size());
        assertEquals(io.github.mathieusoysal.residence.Equipment.WC,
                residence.getEquipements().get(0));
        assertEquals(io.github.mathieusoysal.residence.Equipment.SHOWER,
                residence.getEquipements().get(1));
        assertEquals(1, residence.getOccupationMods().size());
        assertEquals(OccupationKind.ALONE, residence.getOccupationMods().get(0).getOccupationKind());
        assertEquals(33334, residence.getOccupationMods().get(0).getRentMin());
        assertEquals(33334, residence.getOccupationMods().get(0).getRentMax());
    }

}