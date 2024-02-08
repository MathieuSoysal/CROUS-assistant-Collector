package io.github.mathieusoysal.data.managment.collectors.requestors;

import com.github.forax.beautifullogger.Logger;
import com.microsoft.playwright.Playwright;

import io.github.mathieusoysal.exceptions.requests.ApiRequestFailedRuntimeException;

public class RequestorWithoutConnection implements Requestor {

    private static final Logger LOGGER = Logger.getLogger();

    @Override
    public String requestWitGet(String url) {
        LOGGER.info(() -> "Creating profil to request residences");
        String result;
        try (Playwright playwright = Playwright.create()) {
            LOGGER.info(() -> "profil created");
            LOGGER.info(() -> "Requesting residences from " + url);
            var respons = playwright.request().newContext()
                    .head(url, REQUEST_TO_GET_LOGEMENTS);
            if (!respons.ok())
                throw new ApiRequestFailedRuntimeException(respons);
            result = respons.text();
            LOGGER.info(() -> "Residences received");
        }
        LOGGER.info(() -> "profil closed");
        return result;
    }

}
