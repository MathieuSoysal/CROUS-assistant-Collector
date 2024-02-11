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
import io.github.mathieusoysal.exceptions.requests.ApiRequestFailedException;
import io.github.mathieusoysal.residence.Residence;

class DataSaverTest {

    @AfterEach
    void tearDown() {
        File archiveFolder = new File("archive");
        if (archiveFolder.exists())
            archiveFolder.delete();
    }

    @Test
    void testCreateArchiveResidences() throws ApiRequestFailedException, IOException {
        List<Residence> residences = DataCollectorFromCrous.getAllResidencesWithoutConnection().stream().limit(2).toList();
        assertDoesNotThrow(() -> ArchiveSaver.startPath().endPathAndSaveData(ArchiveName.HOUR, residences));
    }

    @Test
    void testCreateArchiveResidencesForDay() throws ApiRequestFailedException, IOException {
        LocalDate chosenDate = LocalDate.of(2024, 1, 10);
        var dataCollector = new DataCollectorFromArchive(
                "https://mathieusoysal.github.io/CROUS-assistant-Collector/v2/residences-crous/available/");
        String data = assertDoesNotThrow(() -> 
            dataCollector.getSumUpOfDay(chosenDate)
        );
        assertDoesNotThrow(
                () -> ArchiveSaver
                        .startPath()
                        .addPath("available")
                        .addPath(chosenDate)
                        .endPathAndSaveData(ArchiveName.DAY_SUM_UP, data));
    }

}
