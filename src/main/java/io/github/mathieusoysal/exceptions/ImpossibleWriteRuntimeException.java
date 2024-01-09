package io.github.mathieusoysal.exceptions;

public class ImpossibleWriteRuntimeException extends RuntimeException {

    public ImpossibleWriteRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImpossibleWriteRuntimeException(String message) {
        super(message);
    }

    public ImpossibleWriteRuntimeException(Throwable cause) {
        super(cause);
    }

}
