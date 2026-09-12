package com.company.api.tests;

import com.company.api.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UsersTests extends BaseTest {

    @Test
    public void getListOfUsers() {
        given()
                .queryParam("page", 2)
        .when()
                .get("/users")
        .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data", hasSize(6))
                .body("data[0].id", notNullValue());
    }

    @Test
    public void getSingleUser() {
        given()
        .when()
                .get("/users/2")
        .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"));
    }

    @Test
    public void getSingleUserNotFound() {
        given()
        .when()
                .get("/users/23")
        .then()
                .statusCode(404);
    }
    
    @Test
    @Description("Get list of users with pagination")
    @Story("Users API")
    public void getListOfUsers1() {
        given()
                .queryParam("page", 2)
        .when()
                .get("/users")
        .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data", hasSize(6))
                .body(matchesJsonSchemaInClasspath("schemas/list-users-schema.json"));
    }

    @Test
    @Description("Get single user and validate schema")
    public void getSingleUserWithSchemaValidation() {
        given()
        .when()
                .get("/users/2")
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"));
    }
}