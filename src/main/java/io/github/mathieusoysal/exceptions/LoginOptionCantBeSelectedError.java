package io.github.mathieusoysal.exceptions;

public class LoginOptionCantBeSelectedError extends Error {
    public LoginOptionCantBeSelectedError(String message, String html) {
        super("Login option can't be selected: " + message + "The html code:\n" + html);
    }
}
