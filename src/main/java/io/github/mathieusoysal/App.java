package io.github.mathieusoysal;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import io.github.mathieusoysal.exceptions.ApiRequestFailedException;
import io.github.mathieusoysal.exceptions.PropertiesNotFoundRuntimeException;
import io.github.mathieusoysal.logement.data.DataCollector;
import io.github.mathieusoysal.logement.data.DataSaver;

public class App {

    private static final String MAIL_PROPERTIES_NAME = "MAIL";
    private static final String PASSWORD_PROPERTIES_NAME = "PASSWORD";

    public static void main(String[] args)
            throws StreamReadException, DatabindException, ApiRequestFailedException, IOException,
            InterruptedException {
        var logements = DataCollector.getAvailableLogementsWithConnection(getEmail(), getPassword());
        DataSaver.createArchiveLogements(logements);
    }

    private static String getEmail() {
        String email = System.getenv(MAIL_PROPERTIES_NAME);
        if (email == null)
            throw new PropertiesNotFoundRuntimeException(MAIL_PROPERTIES_NAME);
        return email;
    }

    private static String getPassword() {
        String password = System.getenv(PASSWORD_PROPERTIES_NAME);
        if (password == null)
            throw new PropertiesNotFoundRuntimeException(PASSWORD_PROPERTIES_NAME);
        return password;
    }

}
