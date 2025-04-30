package com.api.utils;

import com.api.baseclass.BaseTest;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import com.aventstack.extentreports.ExtentTest;


import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import static com.api.baseclass.BaseTest.test;


public class RestAssuredLoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext context) {


        // Capture request
        ByteArrayOutputStream requestOutput = new ByteArrayOutputStream();
        PrintStream requestStream = new PrintStream(requestOutput);

        requestStream.println("Method: " + requestSpec.getMethod());
        requestStream.println("URI: " + requestSpec.getURI());
        requestStream.println("Headers:");
        requestSpec.getHeaders().asList().forEach(header ->
                requestStream.println(header.getName() + ": " + header.getValue()));
        if (requestSpec.getBody() != null) {
            requestStream.println("Body: " + requestSpec.getBody());
        }

        // Execute request
        Response response = context.next(requestSpec, responseSpec);

        // Capture response
        ByteArrayOutputStream responseOutput = new ByteArrayOutputStream();
        PrintStream responseStream = new PrintStream(responseOutput);

        responseStream.println("Status Code: " + response.getStatusCode());
        responseStream.println("Response Body:");
        responseStream.println(response.asPrettyString());

        // Log to Extent Report
        test.get().info("<b>Request:</b><pre>" + requestOutput + "</pre>");
        test.get().info("<b>Response:</b><pre>" + responseOutput + "</pre>");

        return response;
    }
}
