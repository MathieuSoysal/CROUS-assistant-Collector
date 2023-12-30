package io.github.mathieusoysal.logement.data;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.exceptions.ApiRequestFailedException;
import io.github.mathieusoysal.logement.pojo.Logement;

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
        List<Logement> logements = DataCollector.getAllLogementsWithoutConnection().stream().limit(2).toList();
        var file = assertDoesNotThrow(() -> DataSaver.createArchiveLogements(logements));
        file.delete();
    }

}
