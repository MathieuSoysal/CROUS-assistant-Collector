package io.github.mathieusoysal.exceptions;

public class SiteOnMaintenanceException extends RequestWithConnectionFailedException {

    public SiteOnMaintenanceException(String html) {
        super("The site is on maintenance", html);
    }

    public static boolean isSiteOnMaintenance(String html) {
        return html.contains("Maintenance") || html.contains("maintenance");
    }

}
