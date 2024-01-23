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

    public static ArchiveMode getArchiveMode(String archiveMod) {
        try {
            ArchiveMode m = ArchiveMode.valueOf(archiveMod);
            LOGGER.info(() -> "Archive mode is " + m.name());
            return m;
        } catch (IllegalArgumentException e) {
            LOGGER.error(() -> archiveMod + " is not a valid archive mod");
            throw e;
        }
    }

    public static ArchiveMode getArchiveModeFromEnvironmentVariables() {
        return getArchiveMode(Properties.ARCHIVE_MODE.getValue());
    }
}
