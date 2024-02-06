package io.github.mathieusoysal.exceptions;

public class CannotBeConnectedError extends RequestWithConnectionFailedException {
    public CannotBeConnectedError(String message, String html) {
        super("Cannot be connected: " + message + " the html code:\n" + html, html);
    }
}
