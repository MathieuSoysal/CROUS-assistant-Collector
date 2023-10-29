package io.github.mathieusoysal.logement.data;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.RequestOptions;

import io.github.mathieusoysal.exceptions.ApiRequestFailedException;
import io.github.mathieusoysal.logement.pojo.Convertor;
import io.github.mathieusoysal.logement.pojo.Logement;

public class DataCollector {
    private static final String BODY_POST_TO_GET_LOGEMENTS = "{\r\n    \"idTool\": 32,\r\n    \"need_aggregation\": false,\r\n    \"page\": 1,\r\n    \"pageSize\": 2500,\r\n    \"sector\": null,\r\n    \"occupationModes\": [],\r\n    \"location\": [\r\n        {\r\n            \"lon\": -9.9079,\r\n            \"lat\": 51.7087\r\n        },\r\n        {\r\n            \"lon\": 14.3224,\r\n            \"lat\": 40.5721\r\n        }\r\n    ],\r\n    \"residence\": null,\r\n    \"precision\": 9,\r\n    \"equipment\": [],\r\n    \"price\": {\r\n        \"min\": 0,\r\n        \"max\": 10000000\r\n    }\r\n}";
    private static final RequestOptions REQUEST_TO_GET_LOGEMENTS = RequestOptions.create()
            .setMethod("POST")
            .setHeader("Content-Type", "application/json")
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

    public static List<Logement> getAllLogementsWithoutConnection()
            throws ApiRequestFailedException, StreamReadException, DatabindException, IOException {
        List<Logement> logements;
        try (Playwright playwright = Playwright.create()) {
            var respons = playwright.request().newContext()
                    .head("https://trouverunlogement.lescrous.fr/api/fr/search/29", REQUEST_TO_GET_LOGEMENTS);
            if (!respons.ok())
                throw new ApiRequestFailedException(respons);
            logements = Convertor.getLogementsFromBruteJsonString(respons.text());
        }
        return logements;
    }

    public static List<Logement> getAvailableLogementsWithConnection(String email, String password)
            throws ApiRequestFailedException, StreamReadException, DatabindException, IOException {
        List<Logement> logements;
        try (Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                BrowserContext context = browser.newContext();
                Page page = context.newPage()) {
            playwright.selectors().setTestIdAttribute("id");
            page.navigate("https://trouverunlogement.lescrous.fr/tools/32/search");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Identification")).click();
            page.waitForLoadState();
            page.getByTestId("login[app]-label-0").click();
            page.getByAltText("saml-logo").click();
            page.waitForLoadState();
            page.getByPlaceholder("Login (Email)").click();
            page.getByPlaceholder("Login (Email)").fill(email);
            page.getByLabel("Password *").click();
            page.getByLabel("Password *").fill(password);
            page.getByLabel("Password *").press("Enter");
            page.waitForLoadState();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Lancer une recherche"))
                    .click();
            page.waitForLoadState();
            var respons = page.request()
                    .head("https://trouverunlogement.lescrous.fr/api/fr/search/32",
                            REQUEST_TO_GET_LOGEMENTS);
            if (!respons.ok())
                throw new ApiRequestFailedException(respons);
            logements = Convertor.getLogementsFromBruteJsonString(respons.text());
        }
        return logements;
    }
}
