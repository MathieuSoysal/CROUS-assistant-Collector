package io.github.mathieusoysal;

import com.github.forax.beautifullogger.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger();

    public static void main(String[] args) {
        LOGGER.info(() -> "Starting application");
        ArchiveMod archiveMod = ArchiveMod.getArchiveModFromEnvironmentVariables();
        archiveMod.archive();
        LOGGER.info(() -> "Application successfully finished");
    }

}
