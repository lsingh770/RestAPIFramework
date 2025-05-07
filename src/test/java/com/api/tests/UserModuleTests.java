package com.api.tests;


import com.api.baseclass.BaseTest;
import com.api.utils.PayloadReader;
import com.api.utils.RestAssuredLoggingFilter;
import com.api.utils.SpecFactory;
import com.api.utils.TestConfig;
import org.testng.annotations.Test;

import static com.api.baseclass.Tags.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class UserModuleTests extends BaseTest {

    public static final String user_id = TestConfig.get("user.id");

    @Test(groups = {GET})
    public void getUserById() {
        given().filter(new RestAssuredLoggingFilter())
                .spec(SpecFactory.baseSpec())
                .pathParam("id", user_id)
                .when().get("/users/{id}")
                .then().statusCode(200)
                .body("id", equalTo(Integer.parseInt(user_id)));
    }

    @Test(groups = {POST})
    public void createUser() {
        var body = PayloadReader.loadPayload("create_user.json");

        given().filter(new RestAssuredLoggingFilter())
                .spec(SpecFactory.baseSpec())
                .body(body)
                .when().post("/users")
                .then().statusCode(200);
    }

    @Test(groups = {PUT})
    public void updateUser() {
        given().filter(new RestAssuredLoggingFilter())
                .spec(SpecFactory.baseSpec())
                .body("{ \"name\": \"Alice Updated\" }")
                .pathParam("id", user_id)
                .when().put("/users/{id}")
                .then().statusCode(200);
    }

    @Test(groups = {DELETE})
    public void deleteUser() {
        given().filter(new RestAssuredLoggingFilter())
                .spec(SpecFactory.baseSpec())
                .pathParam("id", user_id)
                .when().delete("/users/{id}")
                .then().statusCode(200);
    }
}
