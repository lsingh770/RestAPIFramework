package com.api.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SpecFactory {

    private static final String BASE_URI = TestConfig.get("base.uri");
    private static final String CONTENT_TYPE = TestConfig.get("content.type"); // e.g., application/json
    private static final String APP_ID = "0JyYiOQXQQr5H9OEn21312";

    public static RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addHeader("Content-Type", CONTENT_TYPE)
                .addHeader("app-id", APP_ID)
                .build();
    }
}
