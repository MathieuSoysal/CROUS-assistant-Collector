package io.github.mathieusoysal;

import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.archivers.ArchiverAllLogements;
import io.github.mathieusoysal.archivers.ArchiverDay;
import io.github.mathieusoysal.archivers.ArchiverHour;
import io.github.mathieusoysal.archivers.Archiver;

public enum ArchiveMode {
    DAY_SUM_UP(ArchiverDay::new),
    ALL_LOGEMENTS(ArchiverAllLogements::new),
    HOUR(ArchiverHour::new);

    private static final Logger LOGGER = Logger.getLogger();
    private final Archiver archiver;

    private ArchiveMode(Archiver archiver) {
        this.archiver = archiver;
    }

    public void archive() {
        archiver.archive();
    }

    public static ArchiveMode getArchiveMod(String archiveMod) {
        try {
            return ArchiveMode.valueOf(archiveMod);
        } catch (IllegalArgumentException e) {
            LOGGER.error(() -> archiveMod + " is not a valid archive mod");
            System.exit(1);
            return HOUR;
        }
    }

    public static ArchiveMode getArchiveModFromEnvironmentVariables() {
        if (Properties.ARCHIVE_MODE.isPresent())
            return getArchiveMod(Properties.ARCHIVE_MODE.getValue());
        else
            return HOUR;
    }
}
