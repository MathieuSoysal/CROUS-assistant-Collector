package io.github.mathieusoysal.data.managment.savers;

import java.io.File;
import java.time.OffsetDateTime;

class FolderManager {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger
            .getLogger(FolderManager.class.getName());

    static File getOrCreateArchiveFolder() {
        LOGGER.info(() -> "getting archive folder");
        File archiveFolder = new File("archive");
        if (!archiveFolder.exists()) {
            archiveFolder.mkdir();
            LOGGER.info(() -> "Archive folder created");
        }
        return archiveFolder;
    }

    static File getOrCreateArchiveFolderWithCurrentDate() {
        return getOrCreateArchiveFolderWithGivenDate(OffsetDateTime.now());
    }

    static File getOrCreateArchiveFolderWithGivenDate(OffsetDateTime date) {
        LOGGER.info(() -> "Getting archive folder for current date");
        File archiveFolder = getOrCreateArchiveFolder();
        String archiveFolderName = date.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE);
        File archiveFile = new File(archiveFolder, archiveFolderName);
        if (!archiveFile.exists()) {
            archiveFile.mkdir();
            LOGGER.info(() -> "Archive folder for current date created");
        }
        return archiveFile;
    }
}
