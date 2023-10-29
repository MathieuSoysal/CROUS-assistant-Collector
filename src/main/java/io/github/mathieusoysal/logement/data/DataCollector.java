package io.github.mathieusoysal.logement.data;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

import io.github.mathieusoysal.exceptions.ApiRequestFailedException;
import io.github.mathieusoysal.logement.pojo.Convertor;
import io.github.mathieusoysal.logement.pojo.Logement;

public class DataCollector {
    private static final String BODY_POST_TO_GET_LOGEMENTS = "{\r\n    \"idTool\": 32,\r\n    \"need_aggregation\": false,\r\n    \"page\": 1,\r\n    \"pageSize\": 2500,\r\n    \"sector\": null,\r\n    \"occupationModes\": [],\r\n    \"location\": [\r\n        {\r\n            \"lon\": -9.9079,\r\n            \"lat\": 51.7087\r\n        },\r\n        {\r\n            \"lon\": 14.3224,\r\n            \"lat\": 40.5721\r\n        }\r\n    ],\r\n    \"residence\": null,\r\n    \"precision\": 9,\r\n    \"equipment\": [],\r\n    \"price\": {\r\n        \"min\": 0,\r\n        \"max\": 10000000\r\n    }\r\n}";
    private static final RequestOptions REQUEST_TO_GET_LOGEMENTS = RequestOptions.create()
            .setMethod("POST")
            .setHeader("Content-Type", "application/json")
            .setHeader("Cookie",
                    "SimpleSAMLSessionID=e184a6c92c5ada910ce150e94ef1a98e; qpid=ckumqujfm5tsvkqek740")
            .setData(BODY_POST_TO_GET_LOGEMENTS);

    private DataCollector() {
    }

    public static List<Logement> getAvailableLogementsWithoutConnection()
            throws ApiRequestFailedException, StreamReadException, DatabindException, IOException {
        List<Logement> logements;
        try (Playwright playwright = Playwright.create()) {
            var respons = playwright.request().newContext()
                    .head("https://trouverunlogement.lescrous.fr/api/fr/search/32", REQUEST_TO_GET_LOGEMENTS);
            if (!respons.ok())
                throw new ApiRequestFailedException(respons);
            logements = Convertor.getLogementsFromBruteJsonString(respons.text());
        }
        return logements;
    }

    public static List<Logement> getAllLogementsWithoutConnection() {
        return null;
    }
}
