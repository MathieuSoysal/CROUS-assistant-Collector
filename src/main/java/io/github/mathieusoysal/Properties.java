package io.github.mathieusoysal;

import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.exceptions.PropertiesNotFoundRuntimeException;

public enum Properties {
    LINK_TO_ARCHIVE("LINK_TO_ARCHIVE"),
    MAIL("MAIL"),
    PASSWORD("PASSWORD"),
    ARCHIVE_MOD("ARCHIVE_MOD"),
    SPECIFIC_DAY("DAY_TO_SUM_UP");

    private static final Logger LOGGER = Logger.getLogger();
    private final String name;

    Properties(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isPresent() {
        return System.getenv(name) != null;
    }

    public String getValue() {
        LOGGER.info(() -> "Getting " + name + " from environment variables");
        String propertie = System.getenv(name);
        if (propertie == null) {
            LOGGER.error(() -> name + " not found in environment variables");
            throw new PropertiesNotFoundRuntimeException(name);
        }
        return propertie;
    }

    public static boolean modToArchiveIsActivated() {
        return System.getenv(ARCHIVE_MOD.getName()) != null;
    }
}
