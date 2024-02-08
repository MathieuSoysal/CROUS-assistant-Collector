package io.github.mathieusoysal.exceptions.requests;

public class LoginOptionCantBeSelectedException extends RequestWithConnectionFailedException {
    public LoginOptionCantBeSelectedException(String message, String html) {
        super("Login option can't be selected: " + message + "The html code:\n" + html, html);
    }
}
