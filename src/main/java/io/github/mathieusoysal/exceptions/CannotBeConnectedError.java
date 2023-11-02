package io.github.mathieusoysal.exceptions;

public class CannotBeConnectedError extends Error {
    public CannotBeConnectedError(String message, String html) {
        super("Cannot be connected: " + message + " the html code:\n" + html);
    }
}
