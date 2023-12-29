package io.github.mathieusoysal.logement.data;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BooleanSupplier;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;

import io.github.mathieusoysal.exceptions.ApiRequestFailedException;
import io.github.mathieusoysal.exceptions.CannotBeConnectedError;
import io.github.mathieusoysal.exceptions.LoginOptionCantBeSelectedError;
import io.github.mathieusoysal.logement.pojo.Convertor;
import io.github.mathieusoysal.logement.pojo.Logement;

public class DataCollector {
    private static final String BODY_POST_TO_GET_LOGEMENTS = "{\r\n    \"idTool\": 32,\r\n    \"need_aggregation\": false,\r\n    \"page\": 1,\r\n    \"pageSize\": 2500,\r\n    \"sector\": null,\r\n    \"occupationModes\": [],\r\n    \"location\": [\r\n        {\r\n            \"lon\": -9.9079,\r\n            \"lat\": 51.7087\r\n        },\r\n        {\r\n            \"lon\": 14.3224,\r\n            \"lat\": 40.5721\r\n        }\r\n    ],\r\n    \"residence\": null,\r\n    \"precision\": 9,\r\n    \"equipment\": [],\r\n    \"price\": {\r\n        \"min\": 0,\r\n        \"max\": 10000000\r\n    }\r\n}";
    private static final RequestOptions REQUEST_TO_GET_LOGEMENTS = RequestOptions.create()
            .setMethod("POST")
            .setHeader("Content-Type", "application/json")
            .setData(BODY_POST_TO_GET_LOGEMENTS);

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
            throws ApiRequestFailedException, StreamReadException, DatabindException, IOException,
            InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions());
        BrowserContext context = browser.newContext(new NewContextOptions().setScreenSize(1920, 1080));
        Page page = context.newPage();
        List<Logement> logements;
        try {
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
            goToLoginPage(page);
            selectLoginOption(playwright, page);
            connectToTheCrous(email, password, playwright, page);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Lancer une recherche"))
                    .click();
            page.waitForLoadState();
            var respons = page.request()
                    .head("https://trouverunlogement.lescrous.fr/api/fr/search/32",
                            REQUEST_TO_GET_LOGEMENTS);
            if (!respons.ok())
                throw new ApiRequestFailedException(respons);
            logements = Convertor.getLogementsFromBruteJsonString(respons.text());
        } catch (TimeoutError | LoginOptionCantBeSelectedError | CannotBeConnectedError e) {
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));
            throw e;
        } finally {
            page.close();
            context.close();
            browser.close();
            playwright.close();
        }
        return logements;
    }

    private static void goToLoginPage(Page page) {
        page.navigate("https://trouverunlogement.lescrous.fr/tools/32/search");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Identification")).click();
        page.waitForLoadState();
    }

    private static void selectLoginOption(Playwright playwright, Page page) {
        playwright.selectors().setTestIdAttribute("id");
        String currentUrl = page.url();
        try {
            page.locator("#boxlogin div").nth(0).click();
            if (page.url().equals(currentUrl))
                page.locator("#boxlogin div").filter(new Locator.FilterOptions().setHasText("0")).nth(1).click();
            if (page.url().equals(currentUrl))
                page.getByTestId("login[app]-label-0").click();
            if (page.url().equals(currentUrl))
                page.getByAltText("saml-logo").click();
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
            waitForUrlChange(currentUrl, page);
        } catch (TimeoutError e) {
            throw new LoginOptionCantBeSelectedError(e.getMessage(), page.content());
        }
    }

    private static void connectToTheCrous(String email, String password, Playwright playwright, Page page) {
        playwright.selectors().setTestIdAttribute("type");
        String currentUrl = page.url();
        try {
            fillForm(email, password, page);
            waitForPageLoad(page);
            waitForUrlChange(currentUrl, page);
        } catch (TimeoutError e) {
            throw new CannotBeConnectedError(e.getMessage(), page.content());
        }
    }

    private static void fillForm(String email, String password, Page page) {
        var emailField = page.getByTestId("email");
        emailField.hover();
        emailField.click();
        emailField.fill(email);
        var passwordField = page.getByTestId("password");
        passwordField.click();
        passwordField.fill(password);
        passwordField.press("Enter");
    }

    private static void waitForPageLoad(Page page) {
        page.waitForLoadState();
    }

    private static void waitForUrlChange(String currentUrl, Page page) {
        page.waitForCondition(() -> !page.url().equals(currentUrl));
    }

    private DataCollector() {
    }
}
