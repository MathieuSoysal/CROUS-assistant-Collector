package io.github.mathieusoysal.data.managment.savers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.data.managment.convertors.Convertor;
import io.github.mathieusoysal.exceptions.ImpossibleWriteRuntimeException;
import io.github.mathieusoysal.residence.Residence;

class DataSaver {
    private static final Logger LOGGER = Logger.getLogger();

    static void writeDataInsideArchiveFile(List<Residence> residencesJson, File archiveFile) {
        writeDataInsideArchiveFile(Convertor.convertResidencesToJson(residencesJson), archiveFile);
    }

    static void writeDataInsideArchiveFile(String residencesJson, File archiveFile) {
        LOGGER.info(() -> "Writing residences to file");
        try (FileWriter fileWriter = new FileWriter(archiveFile)) {
            fileWriter.write(residencesJson);
        } catch (IOException e) {
            LOGGER.error("Error while writing residences to file", e);
            throw new ImpossibleWriteRuntimeException("Error while writing residences to file", e);
        }
        LOGGER.info(() -> "Residences written to file");
    }

}
