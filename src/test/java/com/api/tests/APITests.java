package com.api.tests;

import com.api.baseclass.BaseTest;
import com.api.utils.RestAssuredLoggingFilter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.api.baseclass.Tags.*;

public class APITests extends BaseTest {
    protected static RequestSpecification request;

    @BeforeMethod
    public void beforMethod() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        request = RestAssured.given().filter(new RestAssuredLoggingFilter()).header("Content-Type", "application/json");
    }

    @Test(description = "GET Request Test", groups = {GET})
    public void testGetRequest() {
        Response response = request.get("/posts/1");
        logger().info("Sending GET Request");

        Assert.assertEquals(response.getStatusCode(), 200);
        logger().pass("GET Request Passed");
    }

    @Test(description = "POST Request Test", groups = {POST})
    public void testPostRequest() {
        String requestBody = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

        Response response = request.body(requestBody).post("/posts");
        logger().info("Sending POST Request");

        Assert.assertEquals(response.getStatusCode(), 201);
        logger().pass("POST Request Passed");
    }

    @Test(description = "PUT Request Test", groups = {PUT})
    public void testPutRequest() {
        String requestBody = "{\"id\": 1, \"title\": \"fooUpdated\", \"body\": \"barUpdated\", \"userId\": 1}";

        Response response = request.body(requestBody).put("/posts/1");
        logger().info("Sending PUT Request");

        Assert.assertEquals(response.getStatusCode(), 200);
        logger().pass("PUT Request Passed");
    }

    @Test(description = "PATCH Request Test", groups = {PATCH})
    public void testPatchRequest() {
        String requestBody = "{\"title\": \"patchedTitle\"}";

        Response response = request.body(requestBody).patch("/posts/1");
        logger().info("Sending PATCH Request");

        Assert.assertEquals(response.getStatusCode(), 200);
        logger().pass("PATCH Request Passed");
    }

    @Test(description = "DELETE Request Test", groups = {DELETE})
    public void testDeleteRequest() {
        Response response = request.delete("/posts/1");
        logger().info("Sending DELETE Request");

        Assert.assertEquals(response.getStatusCode(), 200);
        logger().pass("DELETE Request Passed");
    }
}