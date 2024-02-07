package io.github.mathieusoysal.archivers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.data.managment.savers.ArchiveName;
import io.github.mathieusoysal.logement.Logement;

class ArchivedLogementsManager {

    private static final Logger LOGGER = Logger.getLogger();

    static void updateArchiveOfAllLogements(List<Logement> collectedLogements) {
        var archivedFile = getArchivedFile(collectedLogements);
        updateHashOfAllLogement(archivedFile);
    }

    private static File getArchivedFile(List<Logement> collectedLogements) {
        var archivedLogements = ArchivedLogements.generateArchivedLogementsFromLinkArchive(Archiver.getLinkToArchive());
        archivedLogements.addLogements(collectedLogements);
        var archivedFile = Archiver.ARCHIVE_SAVER
                .endPathAndSaveData(ArchiveName.ALL_LOGEMENTS, archivedLogements.getLogements());
        return archivedFile;
    }

    private static void updateHashOfAllLogement(File archivedFile) {
        var hash = getHashOfArchivedFile(archivedFile);
        Archiver.ARCHIVE_SAVER
                .endPathAndSaveData(ArchiveName.HASH_ALL_LOGEMENTS, hash);
    }

    static String getHashOfArchivedFile(File archivedFile) {
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

    private static String convertBytesToString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte b : digest)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    private static byte[] getHashedBytesFrom(File archivedFile) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(archivedFile.toPath()));
        return md.digest();
    }
}
