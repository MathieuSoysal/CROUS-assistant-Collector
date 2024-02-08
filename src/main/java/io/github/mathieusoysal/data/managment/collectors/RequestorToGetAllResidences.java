package io.github.mathieusoysal.data.managment.collectors;

import com.github.forax.beautifullogger.Logger;
import com.microsoft.playwright.Playwright;

import io.github.mathieusoysal.data.managment.savers.ArchiveName;
import io.github.mathieusoysal.exceptions.ApiRequestFailedRuntimeException;

class RequestorToGetAllResidences implements Requestor {
    private static final String ALL_LOGEMENTS = "/"+ ArchiveName.ALL_LOGEMENTS.getName();
    private static final Logger LOGGER = Logger.getLogger();

    @Override
    public String requestWitGet(String url) {
        LOGGER.info(() -> "Getting all residences from archive");
        String linkToDataForTheDay = url + ALL_LOGEMENTS;
        String result;
        LOGGER.info(() -> "Creating profil to request residences");
        try (Playwright playwright = Playwright.create()) {
            LOGGER.info(() -> "profil created");
            var context = playwright.request().newContext();
            var respons = context.get(linkToDataForTheDay);
            if (!respons.ok())
                throw new ApiRequestFailedRuntimeException(respons);
            result = respons.text();
            LOGGER.info(() -> "Residences received");
        }
        LOGGER.info(() -> "profil closed");
        return result;
    }

}
