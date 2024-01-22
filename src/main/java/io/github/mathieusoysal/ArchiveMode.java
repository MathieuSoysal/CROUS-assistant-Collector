package io.github.mathieusoysal;

import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.data.managment.archivers.ArchiveAllLogements;
import io.github.mathieusoysal.data.managment.archivers.ArchiveDay;
import io.github.mathieusoysal.data.managment.archivers.ArchiveHour;
import io.github.mathieusoysal.data.managment.archivers.Archiver;

public enum ArchiveMode {
    DAY_SUM_UP(ArchiveDay::new),
    ALL_LOGEMENTS(ArchiveAllLogements::new),
    HOUR(ArchiveHour::new);

    private static final Logger LOGGER = Logger.getLogger();
    private final Archiver archiver;

    ArchiveMode(Archiver archiver) {
        this.archiver = archiver;
    }

    public void archive() {
        archiver.archive();
    }

    public static ArchiveMode getArchiveMod(String archiveMod) {
        try {
            return ArchiveMode.valueOf(archiveMod);
        } catch (IllegalArgumentException e) {
            LOGGER.warning(() -> archiveMod + " is not a valid archive mod");
            return HOUR;
        }
    }

    public static ArchiveMode getArchiveModFromEnvironmentVariables() {
        return getArchiveMod(Properties.ARCHIVE_MODE.getValue());
    }
}
