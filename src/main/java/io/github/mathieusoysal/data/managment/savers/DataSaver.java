package io.github.mathieusoysal.data.managment.savers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromArchive;
import io.github.mathieusoysal.data.managment.convertors.Convertor;
import io.github.mathieusoysal.logement.Logement;

public class DataSaver {
    private static final Logger LOGGER = Logger.getLogger();

    public static File createArchiveLogementsForDay(LocalDate date, final String linkToData) {
        String logementsJson = new DataCollectorFromArchive(linkToData).getSumUpOfDay(date);
        return save(ArchiveName.DAY_SUM_UP, logementsJson);
    }

    public static File createArchiveLogementsForHour(List<Logement> logements) throws JsonProcessingException {
        String logementsJson = Convertor.convertLogementsToJson(logements);
        return save(ArchiveName.HOUR, logementsJson);
    }

    public static File save(ArchiveName archiveName, String content) {
        File archiveFile = FileManager.getOrCreateArchiveFile(archiveName);
        writeDataInsideArchiveFile(content, archiveFile);
        return archiveFile;
    }

    private static void writeDataInsideArchiveFile(String logementsJson, File archiveFile) {
        LOGGER.info(() -> "Writing logements to file");
        try (FileWriter fileWriter = new FileWriter(archiveFile)) {
            fileWriter.write(logementsJson);
        } catch (IOException e) {
            LOGGER.error("Error while writing logements to file", e);
            throw new RuntimeException("Error while writing logements to file", e);
        }
        LOGGER.info(() -> "Logements written to file");
    }

}
