package io.github.mathieusoysal;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.exceptions.ApiRequestFailedException;
import io.github.mathieusoysal.exceptions.PropertiesNotFoundRuntimeException;
import io.github.mathieusoysal.logement.data.DataCollector;
import io.github.mathieusoysal.logement.data.DataSaver;

public class App {
    private static final Logger LOGGER = Logger.getLogger();
    private static final String MAIL_PROPERTIES_NAME = "MAIL";
    private static final String PASSWORD_PROPERTIES_NAME = "PASSWORD";
    private static final String LINK_TO_DATA_PROPERTIE_NAME = "LINK_TO_DATA";

    public static void main(String[] args)
            throws StreamReadException, DatabindException, ApiRequestFailedException, IOException,
            InterruptedException {
        LOGGER.info(() -> "Starting application");
        if (sumupdayModIsActivated())
            DataSaver.createArchiveLogementsForDay(LocalDate.now(), System.getenv(LINK_TO_DATA_PROPERTIE_NAME));
        else
            createArchiveForThisHour();
        LOGGER.info(() -> "Application finished");
    }

    private static boolean sumupdayModIsActivated() {
        return System.getenv(LINK_TO_DATA_PROPERTIE_NAME) != null;
    }

    private static void createArchiveForThisHour()
            throws ApiRequestFailedException, StreamReadException, DatabindException,
            IOException, InterruptedException, JsonProcessingException {
        var logements = DataCollector.getAvailableLogementsWithConnection(getEmail(), getPassword());
        DataSaver.createArchiveLogementsForHour(logements);
    }

    private static String getPropertie(final String propertieName) {
        LOGGER.info(() -> "Getting " + propertieName + " from environment variables");
        String propertie = System.getenv(propertieName);
        if (propertie == null) {
            LOGGER.error(() -> propertieName + " not found in environment variables");
            throw new PropertiesNotFoundRuntimeException(propertieName);
        }
        return propertie;
    }

    private static String getEmail() {
        return getPropertie(MAIL_PROPERTIES_NAME);
    }

    private static String getPassword() {
        return getPropertie(PASSWORD_PROPERTIES_NAME);
    }

}
