package io.github.mathieusoysal.exceptions;

public class PropertiesNotFoundRuntimeException extends RuntimeException {
    
    public PropertiesNotFoundRuntimeException(String propertiesName) {
        super("Properties value " + propertiesName + " not found in environment variables");
    }
    
    public PropertiesNotFoundRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
