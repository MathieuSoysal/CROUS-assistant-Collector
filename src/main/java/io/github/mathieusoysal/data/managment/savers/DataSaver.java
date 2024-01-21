package io.github.mathieusoysal.data.managment.savers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.data.managment.convertors.Convertor;
import io.github.mathieusoysal.exceptions.ImpossibleWriteRuntimeException;
import io.github.mathieusoysal.logement.Logement;

public class DataSaver {
    private static final Logger LOGGER = Logger.getLogger();

    public static File saveForCurrentDay(ArchiveName archiveName, String content) {
        File archiveFile = FileManager.getOrCreateArchiveFileForCurrentDay(archiveName);
        writeDataInsideArchiveFile(content, archiveFile);
        return archiveFile;
    }

    public static File saveForCurrentDay(ArchiveName archiveName, List<Logement> logements) {
        String logementsJson = Convertor.convertLogementsToJson(logements);
        return saveForCurrentDay(archiveName, logementsJson);
    }

    public static File saveForGlobalArchive(ArchiveName archiveName, List<Logement> logements) {
        File archiveFile = FileManager.getOrCreateArchiveFileForGlobal(archiveName);
        String logementsJson = Convertor.convertLogementsToJson(logements);
        writeDataInsideArchiveFile(logementsJson, archiveFile);
        return archiveFile;
    }

    private static void writeDataInsideArchiveFile(String logementsJson, File archiveFile) {
        LOGGER.info(() -> "Writing logements to file");
        try (FileWriter fileWriter = new FileWriter(archiveFile)) {
            fileWriter.write(logementsJson);
        } catch (IOException e) {
            LOGGER.error("Error while writing logements to file", e);
            throw new ImpossibleWriteRuntimeException("Error while writing logements to file", e);
        }
        LOGGER.info(() -> "Logements written to file");
    }

}
