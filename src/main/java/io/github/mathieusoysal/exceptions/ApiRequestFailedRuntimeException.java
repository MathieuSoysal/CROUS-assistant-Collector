package io.github.mathieusoysal.exceptions;

import com.microsoft.playwright.APIResponse;

public class ApiRequestFailedRuntimeException extends RuntimeException {

    public ApiRequestFailedRuntimeException(APIResponse respons) {
        super("API request failed with status code " + respons.status() + " url " + respons.url());
    }

}
