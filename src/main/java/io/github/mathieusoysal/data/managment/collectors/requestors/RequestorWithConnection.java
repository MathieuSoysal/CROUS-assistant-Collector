package io.github.mathieusoysal.data.managment.collectors.requestors;

import com.github.forax.beautifullogger.Logger;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

import io.github.mathieusoysal.exceptions.requests.ApiRequestFailedRuntimeException;
import io.github.mathieusoysal.exceptions.requests.CannotBeConnectedException;
import io.github.mathieusoysal.exceptions.requests.LoginOptionCantBeSelectedException;
import io.github.mathieusoysal.exceptions.requests.RequestFailedRuntimeException;
import io.github.mathieusoysal.exceptions.requests.SiteOnMaintenanceException;

public class RequestorWithConnection implements Requestor {

    private final String email;
    private final String password;

    public RequestorWithConnection(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String requestWitGet(String url) {
        LOGGER.info(() -> "Getting available residences");
        LOGGER.info(() -> "Creating profil to request residences");

        String jsonResidences;
        try (
                Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions());
                BrowserContext context = browser.newContext(new NewContextOptions().setScreenSize(1920, 1080));
                Page page = context.newPage();) {
            etablishConnectionWithWebsite(playwright, context, page);
            LOGGER.info(() -> "Requesting residences from " + url);
            var respons = page.request()
                    .head(url,
                            REQUEST_TO_GET_LOGEMENTS);
            if (!respons.ok()) {
                LOGGER.error(() -> "Request failed");
                throw new ApiRequestFailedRuntimeException(respons);
            }
            LOGGER.info(() -> "Residences received");
            jsonResidences = respons.text();
        } catch (TimeoutError | LoginOptionCantBeSelectedException | CannotBeConnectedException e) {
            LOGGER.error("Request failed", e);
            throw new RequestFailedRuntimeException(e);
        } catch (SiteOnMaintenanceException e) {
            LOGGER.warning(() -> "Site on maintenance");
            jsonResidences = "[]";
        } finally {
            LOGGER.info(() -> "profil closed");
        }
        return jsonResidences;
    }

    private void etablishConnectionWithWebsite(Playwright playwright, BrowserContext context, Page page)
            throws LoginOptionCantBeSelectedException, CannotBeConnectedException, SiteOnMaintenanceException {
        goToLoginPage(page);
        try {
            selectLoginOption(playwright, page);
        } catch (LoginOptionCantBeSelectedException e) {
            LOGGER.warning(() -> "Can't connect to the crous");
            selectLoginOption(playwright, page);
        }
        connectToTheCrous(email, password, playwright, page);
        LOGGER.info(() -> "Going to residences page");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Lancer une recherche"))
                .click();
        page.waitForLoadState();
    }

    private static final Logger LOGGER = Logger.getLogger();

    private static void goToLoginPage(Page page) {
        LOGGER.info(() -> "Going to login page");
        page.navigate("https://trouverunlogement.lescrous.fr/tools/32/search");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Identification")).click();
        page.waitForLoadState();
    }

    static void selectLoginOption(Playwright playwright, Page page)
            throws LoginOptionCantBeSelectedException, SiteOnMaintenanceException {
        playwright.selectors().setTestIdAttribute("id");
        String currentUrl = page.url();
        LOGGER.info(() -> "Selecting login option");
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
            LOGGER.error(() -> "Login option can't be selected");
            if (SiteOnMaintenanceException.isSiteOnMaintenance(page.content()))
                throw new SiteOnMaintenanceException(page.content());
            throw new LoginOptionCantBeSelectedException(e.getMessage(), page.content());
        }
        LOGGER.info(() -> "Login option selected");
    }

    private static void connectToTheCrous(String email, String password, Playwright playwright, Page page)
            throws CannotBeConnectedException {
        LOGGER.info(() -> "Connecting to the crous");
        playwright.selectors().setTestIdAttribute("type");
        String currentUrl = page.url();
        try {
            fillForm(email, password, page);
            waitForPageLoad(page);
            waitForUrlChange(currentUrl, page);
        } catch (TimeoutError e) {
            LOGGER.error(() -> "Can't connect to the crous");
            throw new CannotBeConnectedException(e.getMessage(), page.content());
        }
        LOGGER.info(() -> "Connected to the crous");
    }

    private static void fillForm(String email, String password, Page page) {
        LOGGER.info(() -> "Filling form");
        var emailField = page.getByTestId("email");
        emailField.hover();
        emailField.click();
        emailField.fill(email);
        var passwordField = page.getByTestId("password");
        passwordField.click();
        passwordField.fill(password);
        LOGGER.info(() -> "Form filled");
        LOGGER.info(() -> "Submitting form");
        passwordField.press("Enter");
    }

    private static void waitForPageLoad(Page page) {
        page.waitForLoadState();
    }

    private static void waitForUrlChange(String currentUrl, Page page) {
        page.waitForCondition(() -> !page.url().equals(currentUrl));
    }

}
