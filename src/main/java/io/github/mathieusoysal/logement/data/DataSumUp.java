package io.github.mathieusoysal.logement.data;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.forax.beautifullogger.Logger;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

import io.github.mathieusoysal.exceptions.ApiRequestErrorRuntimeException;
import io.github.mathieusoysal.exceptions.ConvertionErrorRuntimeException;
import io.github.mathieusoysal.logement.pojo.Convertor;
import io.github.mathieusoysal.logement.pojo.Logement;

public class DataSumUp {
    private static final Logger LOGGER = Logger.getLogger();
    private static final NumberFormat NUMBER_FORMAT = new DecimalFormat("00");

    public static String createSumUpOfTheDay(LocalDate date, String linkToData) throws JsonProcessingException {
        LOGGER.info(() -> "Creating sum up of the day: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        String linkToDataForTheDay = linkToData + "/" + date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String sumUp = "";
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info(() -> "Creating profil to request logements");
        try (Playwright playwright = Playwright.create()) {
            LOGGER.info(() -> "profil created");
            var context = playwright.request().newContext();
            sumUp = objectMapper.writeValueAsString(IntStream.range(0, 24)
                    .mapToObj(hour -> linkToDataForTheDay + "/" + NUMBER_FORMAT.format(hour))
                    .map(link -> getFromUrl(link, context))
                    .toArray());
            LOGGER.info(() -> "Logements received");
        }
        LOGGER.info(() -> "profil closed");
        return sumUp;
    }

    private static List<Logement> getFromUrl(String url, APIRequestContext context) {
        LOGGER.info(() -> "Getting data from url: " + url);
        var respons = context.get(url);
        if (!respons.ok())
            throw new ApiRequestErrorRuntimeException(respons);
        LOGGER.info(() -> "Data received");
        try {
            return Convertor.getLogementsFromBruteJsonString(respons.text());
        } catch (IOException e) {
            LOGGER.error(() -> "Error while parsing json: " + respons.text());
            throw new ConvertionErrorRuntimeException(respons.text(), e);
        }
    }

}
