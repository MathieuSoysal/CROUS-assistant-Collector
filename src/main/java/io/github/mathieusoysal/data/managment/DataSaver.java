package io.github.mathieusoysal.data.managment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromArchive;
import io.github.mathieusoysal.logement.Logement;

public class DataSaver {
    private static final Logger LOGGER = Logger.getLogger();

    public static File createArchiveFolder() {
        LOGGER.info(() -> "getting archive folder");
        File archiveFolder = new File("archive");
        if (!archiveFolder.exists()) {
            archiveFolder.mkdir();
            LOGGER.info(() -> "Archive folder created");
        }

        return archiveFolder;
    }

    public static File createArchiveLogementsForDay(LocalDate date, final String linkToData) {
        File archiveFolder = getArchiveFolderForCurrentDate();
        var dataCollectorFromArchive = new DataCollectorFromArchive(linkToData);
        String logementsJson = dataCollectorFromArchive.getSumUpOfDay(date);
        File archiveFile = getArchiveFileForDay(archiveFolder);
        writeLogementsDataInsideArchiveFile(logementsJson, archiveFile);
        return archiveFile;
    }

    public static File createArchiveLogementsForHour(List<Logement> logements) throws JsonProcessingException {
        File archiveFolder = getArchiveFolderForCurrentDate();
        String logementsJson = convertLogementsToJson(logements);
        File archiveFile = getArchiveFileForHour(archiveFolder);
        writeLogementsDataInsideArchiveFile(logementsJson, archiveFile);
        return archiveFile;
    }

    private static void writeLogementsDataInsideArchiveFile(String logementsJson, File archiveFile) {
        LOGGER.info(() -> "Writing logements to file");
        try (FileWriter fileWriter = new FileWriter(archiveFile)) {
            fileWriter.write(logementsJson);
        } catch (IOException e) {
            LOGGER.error("Error while writing logements to file", e);
            throw new RuntimeException("Error while writing logements to file", e);
        }
        LOGGER.info(() -> "Logements written to file");
    }

    private static File getArchiveFileForHour(File archiveFolder) {
        return getArchiveFile(archiveFolder,
                OffsetDateTime.now().toLocalTime().format(DateTimeFormatter.ofPattern("HH")));
    }

    private static File getArchiveFileForDay(File archiveFolder) {
        return getArchiveFile(archiveFolder, "sum-up");
    }

    private static File getArchiveFile(File archiveFolder, String archiveFileName) throws DateTimeException {
        LOGGER.info(() -> "Getting archive file");
        Stream.of(archiveFolder.listFiles())
                .filter(file -> file.getName().equals(archiveFileName))
                .findFirst()
                .ifPresent(file -> {
                    LOGGER.warning(() -> "Archive file already exists");
                    try {
                        Files.delete(file.toPath());
                    } catch (IOException e) {
                        LOGGER.error("Error while deleting archive file", e);
                        e.printStackTrace();
                    }
                    LOGGER.info(() -> "Archive file deleted");
                });
        var archiveFile = new File(archiveFolder, archiveFileName);
        LOGGER.info(() -> "Archive file got");
        return archiveFile;
    }

    private static String convertLogementsToJson(List<Logement> logements) throws JsonProcessingException {
        LOGGER.info(() -> "Converting logements to json");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        var result = ow.writeValueAsString(logements);
        LOGGER.info(() -> "Logements converted to json");
        return result;
    }

    private static File getArchiveFolderForCurrentDate() {
        LOGGER.info(() -> "Getting archive folder for current date");
        File archiveFolder = createArchiveFolder();
        String archiveFolderName = OffsetDateTime.now().toLocalDate().toString();
        File archiveFile = new File(archiveFolder, archiveFolderName);
        if (!archiveFile.exists()) {
            archiveFile.mkdir();
            LOGGER.info(() -> "Archive folder for current date created");
        }
        return archiveFile;
    }

}
