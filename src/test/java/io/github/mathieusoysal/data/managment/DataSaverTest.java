package io.github.mathieusoysal.data.managment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromCrous;
import io.github.mathieusoysal.exceptions.ApiRequestFailedException;
import io.github.mathieusoysal.logement.Logement;

class DataSaverTest {

    @AfterEach
    void tearDown() {
        File archiveFolder = new File("archive");
        if (archiveFolder.exists())
            archiveFolder.delete();
    }

    @Test
    void testCreateArchiveFolder() {
        File archiveFolder = DataSaver.createArchiveFolder();

        assertTrue(archiveFolder.exists());
        assertTrue(archiveFolder.isDirectory());
        assertEquals("archive", archiveFolder.getName());
        archiveFolder.delete();
    }

    @Test
    void testCreateArchiveLogements() throws ApiRequestFailedException, IOException {
        List<Logement> logements = DataCollectorFromCrous.getAllLogementsWithoutConnection().stream().limit(2).toList();
        var file = assertDoesNotThrow(() -> DataSaver.createArchiveLogementsForHour(logements));
        file.delete();
    }

    @Test
    void testCreateArchiveLogementsForDay() throws ApiRequestFailedException, IOException {
        assertDoesNotThrow(() -> DataSaver.createArchiveLogementsForDay(LocalDate.of(2024, 1, 3),
                "https://mathieusoysal.github.io/CROUS-assistant-Collector/v1/logements-crous/available/"));
    }

}
