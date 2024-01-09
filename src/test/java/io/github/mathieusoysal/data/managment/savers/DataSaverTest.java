package io.github.mathieusoysal.data.managment.savers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromArchive;
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
    void testCreateArchiveLogements() throws ApiRequestFailedException, IOException {
        List<Logement> logements = DataCollectorFromCrous.getAllLogementsWithoutConnection().stream().limit(2).toList();
        var file = assertDoesNotThrow(() -> DataSaver.save(ArchiveName.HOUR, logements));
        file.delete();
    }

    @Test
    void testCreateArchiveLogementsForDay() throws ApiRequestFailedException, IOException {
        var dataCollector = new DataCollectorFromArchive("https://mathieusoysal.github.io/CROUS-assistant-Collector/v1/logements-crous/available/");
        assertDoesNotThrow(() -> dataCollector.getSumUpOfDay(LocalDate.of(2024, 1, 3)));
    }

}
