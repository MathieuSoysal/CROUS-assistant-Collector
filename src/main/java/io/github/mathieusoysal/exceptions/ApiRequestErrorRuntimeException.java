package io.github.mathieusoysal.exceptions;

import com.microsoft.playwright.APIResponse;

public class ApiRequestErrorRuntimeException extends RuntimeException {
    public ApiRequestErrorRuntimeException(String message) {
        super("Api request failed: " + message);
    }

    public ApiRequestErrorRuntimeException(String message, Throwable cause) {
        super("Api request failed: " + message, cause);
    }

    public ApiRequestErrorRuntimeException(APIResponse respons, Throwable cause) {
        super("Api request failed with status code " + respons.status(), cause);
    }

    public ApiRequestErrorRuntimeException(APIResponse respons) {
        super("Api request failed with status code " + respons.status() + " : " + respons.url());
    }

}
