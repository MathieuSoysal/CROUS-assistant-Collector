package io.github.mathieusoysal.exceptions;

public class ConvertionRuntimeException extends RuntimeException {

    public ConvertionRuntimeException(String message) {
        super("Convertion failed: " + message);
    }

    public ConvertionRuntimeException(String message, Throwable cause) {
        super("Convertion failed: " + message, cause);
    }

}
