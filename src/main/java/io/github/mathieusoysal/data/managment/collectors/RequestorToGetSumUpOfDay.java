package io.github.mathieusoysal.data.managment.collectors;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

import com.github.forax.beautifullogger.Logger;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

import io.github.mathieusoysal.data.managment.convertors.Convertor;
import io.github.mathieusoysal.exceptions.ApiRequestErrorRuntimeException;
import io.github.mathieusoysal.logement.Logement;

class RequestorToGetSumUpOfDay implements Requestor {
    private static final Logger LOGGER = Logger.getLogger();
    private static final NumberFormat NUMBER_FORMAT = new DecimalFormat("00");

    private final String date;

    public RequestorToGetSumUpOfDay(LocalDate date) {
        this.date = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public RequestorToGetSumUpOfDay(String date) {
        this.date = date;
    }

    @Override
    public String requestWitGet(String url) {
        var sumUp = getSumUpOfDay(url);
        return Convertor.convertLogementMatrixToJson(sumUp);
    }

    public Logement[][] getSumUpOfDay(String url) {
        LOGGER.info(() -> "Creating sum up of the day: " + date);
        String linkToDataForTheDay = url + "/" + date;
        Logement[][] sumUp;
        LOGGER.info(() -> "Creating profil to request logements");
        try (Playwright playwright = Playwright.create()) {
            LOGGER.info(() -> "profil created");
            var context = playwright.request().newContext();
            sumUp = IntStream.range(0, 24)
                    .<String>mapToObj(hour -> linkToDataForTheDay + "/" + NUMBER_FORMAT.format(hour))
                    .<Logement[]>map(link -> getFromUrl(link, context))
                    .toArray(Logement[][]::new);
            LOGGER.info(() -> "Logements received");
        } 
        LOGGER.info(() -> "profil closed");
        return sumUp;
    }

    private static Logement[] getFromUrl(String url, APIRequestContext context) {
        LOGGER.info(() -> "Getting data from url: " + url);
        var respons = context.get(url);
        if (!respons.ok())
            throw new ApiRequestErrorRuntimeException(respons);
        LOGGER.info(() -> "Data received");
        return Convertor.convertJsonToArrayOfLogements(respons.text());
    }

}
