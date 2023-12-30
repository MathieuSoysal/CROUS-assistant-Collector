package io.github.mathieusoysal;

import java.io.IOException;

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

    public static void main(String[] args)
            throws StreamReadException, DatabindException, ApiRequestFailedException, IOException,
            InterruptedException {
        LOGGER.info(() -> "Starting application");
        var logements = DataCollector.getAvailableLogementsWithConnection(getEmail(), getPassword());
        DataSaver.createArchiveLogements(logements);
        LOGGER.info(() -> "Application finished");
    }

    private static String getEmail() {
        LOGGER.info(() -> "Getting email from environment variables");
        String email = System.getenv(MAIL_PROPERTIES_NAME);
        if (email == null)
        {
            LOGGER.error(() -> "Email not found in environment variables");
            throw new PropertiesNotFoundRuntimeException(MAIL_PROPERTIES_NAME);
        }
        return email;
    }

    private static String getPassword() {
        LOGGER.info(() -> "Getting password from environment variables");
        String password = System.getenv(PASSWORD_PROPERTIES_NAME);
        if (password == null)
        {
            LOGGER.error(() -> "Password not found in environment variables");
            throw new PropertiesNotFoundRuntimeException(PASSWORD_PROPERTIES_NAME);
        }
        return password;
    }

}
