package io.github.mathieusoysal.exceptions;

public class RequestWithConnectionFailedException extends Exception {

    private final String html;

    public RequestWithConnectionFailedException(String message, String html) {
        super("Request with connection failed: " + message);
        this.html = html;
    }

    public String getHtml() {
        return html;
    }

    public static boolean isCausedByRequestWithConnectionFailedException(Throwable throwable) {
        return throwable instanceof RequestWithConnectionFailedException;
    }

}
