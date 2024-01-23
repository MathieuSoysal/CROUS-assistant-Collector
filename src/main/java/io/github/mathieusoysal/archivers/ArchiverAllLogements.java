package io.github.mathieusoysal.archivers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromArchive;
import io.github.mathieusoysal.data.managment.savers.ArchiveName;
import io.github.mathieusoysal.data.managment.savers.DataSaver;
import io.github.mathieusoysal.logement.LogementsClassifier;

public class ArchiverAllLogements implements Archiver {

    private static final Logger LOGGER = Logger.getLogger();

    @Override
    public void archive() {
        var archivedFile = archiveAllLogements();
        updateHashOfAllLogement(archivedFile);
    }

    private void updateHashOfAllLogement(File archivedFile) {
        var hash = getHashOfArchivedFile(archivedFile);
        DataSaver.saveForGlobalArchive(ArchiveName.HASH_ALL_LOGEMENTS, hash);
    }

    private File archiveAllLogements() {
        var dataCollector = new DataCollectorFromArchive(Archiver.getLinkToArchive());
        var logements = new LogementsClassifier();
        logements.addLogements(dataCollector.getAllLogements());
        logements.addLogements(dataCollector.getConvertedSumUpOfDay(Archiver.getDayToArchive()));
        return DataSaver.saveForGlobalArchive(ArchiveName.ALL_LOGEMENTS, logements.getLogements());
    }

    String getHashOfArchivedFile(File archivedFile) {
        String result = "";
        try {
            byte[] digest = getHashedBytesFrom(archivedFile);
            result = convertBytesToString(digest);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Error while getting hash of archived file the MD5 algorithm doesn't found in system", e);
            System.exit(1);
        } catch (IOException e) {
            LOGGER.error("Error while reading archived file", e);
            System.exit(1);
        }
        return result;
    }

    private String convertBytesToString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte b : digest)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    private byte[] getHashedBytesFrom(File archivedFile) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(archivedFile.toPath()));
        return md.digest();
    }

}
