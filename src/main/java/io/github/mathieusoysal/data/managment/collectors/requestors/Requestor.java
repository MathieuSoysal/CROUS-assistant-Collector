package io.github.mathieusoysal.data.managment.collectors.requestors;

import com.microsoft.playwright.options.RequestOptions;

public interface Requestor {
    static final String BODY_POST_TO_GET_LOGEMENTS = "{\r\n    \"idTool\": 32,\r\n    \"need_aggregation\": false,\r\n    \"page\": 1,\r\n    \"pageSize\": 2500,\r\n    \"sector\": null,\r\n    \"occupationModes\": [],\r\n    \"location\": [\r\n        {\r\n            \"lon\": -9.9079,\r\n            \"lat\": 51.7087\r\n        },\r\n        {\r\n            \"lon\": 14.3224,\r\n            \"lat\": 40.5721\r\n        }\r\n    ],\r\n    \"residence\": null,\r\n    \"precision\": 9,\r\n    \"equipment\": [],\r\n    \"price\": {\r\n        \"min\": 0,\r\n        \"max\": 10000000\r\n    }\r\n}";

    static final RequestOptions REQUEST_TO_GET_LOGEMENTS = RequestOptions.create()
            .setMethod("POST")
            .setHeader("Content-Type", "application/json")
            .setData(BODY_POST_TO_GET_LOGEMENTS);

    String requestWitGet(String url);
}
