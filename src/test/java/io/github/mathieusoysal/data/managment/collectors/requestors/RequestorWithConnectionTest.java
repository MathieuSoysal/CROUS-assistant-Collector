package io.github.mathieusoysal.data.managment.collectors.requestors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Selectors;
import com.microsoft.playwright.TimeoutError;

import io.github.mathieusoysal.exceptions.requests.SiteOnMaintenanceException;

public class RequestorWithConnectionTest {

    @Test
    void testSelectLoginOption_withSiteOnMaintenance_throwsSiteOnMaintenanceException() {
        // Arrange
        Playwright pw = spy(Playwright.class);
        Page page = mock(Page.class);
        Selectors selectors = mock(Selectors.class);

        // Act
        when(page.url()).thenReturn("https://www.demande-logement-social.gouv.fr/demande-en-ligne");
        when(page.locator(any())).thenThrow(new TimeoutError(""));
        when(page.content()).thenReturn("Site en maintenance");
        when(pw.selectors()).thenReturn(selectors);
        doNothing().when(selectors).setTestIdAttribute(anyString());

        // Assert
        assertThrows(SiteOnMaintenanceException.class, () -> RequestorWithConnection.selectLoginOption(pw, page));
    }
}
