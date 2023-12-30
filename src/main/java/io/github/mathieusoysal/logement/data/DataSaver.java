package io.github.mathieusoysal.logement.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.github.mathieusoysal.logement.pojo.Logement;

public class DataSaver {

    public static File createArchiveFolder() {
        File archiveFolder = new File("archive");
        if (!archiveFolder.exists())
            archiveFolder.mkdir();
        return archiveFolder;
    }

    public static File createArchiveLogements(List<Logement> logements) throws JsonProcessingException {
        File archiveFolder = getArchiveFolderForCurrentDate();
        String logementsJson = convertLogementsToJson(logements);
        File archiveFile = getArchiveFile(archiveFolder);
        writeLogementsDataInsideArchiveFile(logementsJson, archiveFile);
        return archiveFile;
    }

    private static void writeLogementsDataInsideArchiveFile(String logementsJson, File archiveFile) {
        try (FileWriter fileWriter = new FileWriter(archiveFile)) {
            fileWriter.write(logementsJson);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing logements to file", e);
        }
    }

    private static File getArchiveFile(File archiveFolder) throws DateTimeException {
        String archiveFileName = OffsetDateTime.now().toLocalTime().format(DateTimeFormatter.ofPattern("HH"));
        Stream.of(archiveFolder.listFiles())
                .filter(file -> file.getName().equals(archiveFileName))
                .findFirst()
                .ifPresent(File::delete);
        return new File(archiveFolder, archiveFileName);
    }

    private static String convertLogementsToJson(List<Logement> logements) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(logements);
    }

    private static File getArchiveFolderForCurrentDate() {
        File archiveFolder = createArchiveFolder();
        String archiveFolderName = OffsetDateTime.now().toLocalDate().toString();
        File archiveFile = new File(archiveFolder, archiveFolderName);
        if (!archiveFile.exists())
            archiveFile.mkdir();
        return archiveFile;
    }

}
