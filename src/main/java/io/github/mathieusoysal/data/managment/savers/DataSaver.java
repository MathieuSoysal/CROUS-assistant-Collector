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



    static void writeDataInsideArchiveFile(List<Logement> logementsJson, File archiveFile) {
        writeDataInsideArchiveFile(Convertor.convertLogementsToJson(logementsJson), archiveFile);
    }

    static void writeDataInsideArchiveFile(String logementsJson, File archiveFile) {
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
