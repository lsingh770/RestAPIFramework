package com.api.tests;

import com.api.baseclass.BaseTest;
import com.api.utils.PayloadReader;
import com.api.utils.RestAssuredLoggingFilter;
import com.api.utils.SpecFactory;
import com.api.utils.TestConfig;
import org.testng.annotations.Test;

import static com.api.baseclass.Tags.*;
import static io.restassured.RestAssured.given;

public class OrderModuleTests extends BaseTest {

    public static final String order_id = TestConfig.get("order.id");

    @Test(groups = {GET})
    public void getOrderById() {
        given().filter(new RestAssuredLoggingFilter())
                .spec(SpecFactory.baseSpec()).pathParam("id", order_id)
                .when().get("/orders/{id}")
                .then().statusCode(200);
    }

    @Test(groups = {POST})
    public void createOrder() {

        String jsonBody = PayloadReader.loadPayload("create_order.json");

        given().filter(new RestAssuredLoggingFilter())
                .spec(SpecFactory.baseSpec())
                .body(jsonBody)
                .when().post("/orders")
                .then().statusCode(201);
    }

    @Test(groups = {PUT})
    public void updateOrder() {
        given().filter(new RestAssuredLoggingFilter())
                .spec(SpecFactory.baseSpec())
                .body("{ \"quantity\": 3 }")
                .pathParam("id", order_id)
                .when().put("/orders/{id}")
                .then().statusCode(200);
    }

    @Test(groups = {DELETE})
    public void deleteOrder() {
        given().filter(new RestAssuredLoggingFilter())
                .spec(SpecFactory.baseSpec())
                .pathParam("id", order_id)
                .when().delete("/orders/{id}")
                .then().statusCode(204);
    }
}
