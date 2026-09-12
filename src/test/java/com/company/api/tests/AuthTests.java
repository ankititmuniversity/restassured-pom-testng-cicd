package com.company.api.tests;

import com.company.api.base.BaseTest;
import com.company.api.pojos.LoginRequest;
import com.company.api.pojos.LoginResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthTests extends BaseTest {

    @Test
    public void successfulLogin() {
        LoginRequest loginRequest = new LoginRequest("eve.holt@reqres.in", "cityslicka");

        LoginResponse response = given()
                .body(loginRequest)
        .when()
                .post("/login")
        .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract().as(LoginResponse.class);

        System.out.println("Token: " + response.getToken());
    }

//    @Test(retryAnalyzer = com.company.api.listeners.RetryAnalyzer.class)
//    public void unsuccessfulLogin() {
//        LoginRequest loginRequest = new LoginRequest("peter@klaven", "");
//
//        given()
//                .body(loginRequest)
//        .when()
//                .post("/login")
//        .then()
//                .statusCode(400)
//                .body("error", equalTo("Missing password"));
//    }
}