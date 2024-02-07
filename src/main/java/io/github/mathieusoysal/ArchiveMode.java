package io.github.mathieusoysal;

import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.archivers.Archiver;
import io.github.mathieusoysal.archivers.Archivers;

public enum ArchiveMode {
    DAY_SUM_UP(Archivers.ARCHIVER_DAY),
    HOUR(Archivers.ARCHIVER_HOUR);

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
