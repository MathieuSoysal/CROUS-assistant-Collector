package io.github.mathieusoysal.data.managment.savers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.DateTimeException;
import java.util.stream.Stream;

import com.github.forax.beautifullogger.Logger;

class FileManager {
    private static final Logger LOGGER = Logger.getLogger();

    static File getOrCreateArchiveFileForCurrentDay(ArchiveName archiveName) {
        var archiveFolder = FolderManager.getOrCreateArchiveFolderWithCurrentDate();
        return getArchiveFile(archiveFolder, archiveName.getName());
    }

    static File getOrCreateArchiveFileForGlobal(ArchiveName archiveName) {
        var archiveFolder = FolderManager.getOrCreateArchiveFolder();
        return getArchiveFile(archiveFolder, archiveName.getName());
    }

    static File getArchiveFile(File archiveFolder, String archiveFileName) throws DateTimeException {
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
}
