package io.github.mathieusoysal;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import io.github.mathieusoysal.exceptions.ApiRequestFailedException;
import io.github.mathieusoysal.logement.data.DataCollector;
import io.github.mathieusoysal.logement.data.DataSaver;

public class App {

    public static void main(String[] args)
            throws StreamReadException, DatabindException, ApiRequestFailedException, IOException {
        var logements = DataCollector.getAvailableLogementsWithoutConnection();
        DataSaver.createArchiveLogements(logements);
    }

}
