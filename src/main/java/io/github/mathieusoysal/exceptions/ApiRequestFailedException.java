package io.github.mathieusoysal.exceptions;

import com.microsoft.playwright.APIResponse;

public class ApiRequestFailedException extends Exception {

    public ApiRequestFailedException(APIResponse respons) {
        super("API request failed with status code " + respons.status());
    }

}
