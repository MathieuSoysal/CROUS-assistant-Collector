package io.github.mathieusoysal.exceptions.requests;

public class RequestFailedRuntimeException extends RuntimeException {
    

    public RequestFailedRuntimeException(String message) {
        super(message);
    }

    public RequestFailedRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestFailedRuntimeException(Throwable cause) {
        super(cause);
    }
}
