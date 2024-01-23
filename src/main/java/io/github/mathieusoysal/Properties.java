package io.github.mathieusoysal;

import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.exceptions.PropertiesNotFoundRuntimeException;

public enum Properties {
    LINK_TO_ARCHIVE("LINK_TO_ARCHIVE"),
    MAIL("MAIL"),
    PASSWORD("PASSWORD"),
    ARCHIVE_MODE("ARCHIVE_MODE"),
    SPECIFIC_DAY("SPECIFIC_DAY");

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
}
