package io.github.mathieusoysal.exceptions;

public class ConvertionErrorRuntimeException extends RuntimeException {

    public ConvertionErrorRuntimeException(String message) {
        super("Convertion failed: " + message);
    }

    public ConvertionErrorRuntimeException(String message, Throwable cause) {
        super("Convertion failed: " + message, cause);
    }

}
