package io.github.mathieusoysal.exceptions.requests;

public class CannotBeConnectedException extends RequestWithConnectionFailedException {
    public CannotBeConnectedException(String message, String html) {
        super("Cannot be connected: " + message + " the html code:\n" + html, html);
    }
}
