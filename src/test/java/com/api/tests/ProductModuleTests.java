package com.api.tests;

import com.api.baseclass.BaseTest;
import com.api.utils.PayloadReader;
import com.api.utils.RestAssuredLoggingFilter;
import com.api.utils.SpecFactory;
import com.api.utils.TestConfig;
import org.testng.annotations.Test;

import static com.api.baseclass.Tags.*;
import static io.restassured.RestAssured.*;


public class ProductModuleTests extends BaseTest {

    public static final String product_id = TestConfig.get("product.id");

    @Test(groups = {GET})
    public void getProductById() {
        given().filter(new RestAssuredLoggingFilter())
                .spec(SpecFactory.baseSpec())
                .pathParam("id", product_id)
                .when().get("/products/{id}")
                .then().statusCode(200);
    }

    @Test(groups = {POST})
    public void createProduct() {
        var jsonBody = PayloadReader.loadPayload("create_product.son");

        given().filter(new RestAssuredLoggingFilter())
                .spec(SpecFactory.baseSpec())
                .body(jsonBody)
                .when().post("/products")
                .then().statusCode(201);
    }

    @Test(groups = {PATCH})
    public void updateProductPartial() {
        given().filter(new RestAssuredLoggingFilter())
                .spec(SpecFactory.baseSpec())
                .body("{ \"price\": 899 }")
                .pathParam("id", product_id)
                .when().patch("/products/{id}")
                .then().statusCode(200);
    }

    @Test(groups = {DELETE})
    public void deleteProduct() {
        given().filter(new RestAssuredLoggingFilter())
                .spec(SpecFactory.baseSpec())
                .pathParam("id", product_id)
                .when().delete("/products/{id}")
                .then().statusCode(204);
    }
}
