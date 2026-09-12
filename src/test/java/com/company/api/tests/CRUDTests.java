package com.company.api.tests;

import com.company.api.base.BaseTest;
import com.company.api.pojos.UserRequest;
import com.company.api.pojos.UserResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CRUDTests extends BaseTest {

    @Test
    public void createUser() {
        UserRequest userRequest = new UserRequest("Morpheus", "Leader");

        UserResponse response = given()
                .body(userRequest)
        .when()
                .post("/users")
        .then()
                .statusCode(201)
                .body("name", equalTo("Morpheus"))
                .body("job", equalTo("Leader"))
                .body("id", notNullValue())
                .extract().as(UserResponse.class);

        System.out.println("Created User ID: " + response.getId());
    }

    @Test
    public void updateUser() {
        UserRequest userRequest = new UserRequest("Morpheus", "Zion Resident");

        given()
                .body(userRequest)
        .when()
                .put("/users/2")
        .then()
                .statusCode(200)
                .body("name", equalTo("Morpheus"))
                .body("job", equalTo("Zion Resident"));
    }

//    @Test
//    public void deleteUser() {
//        given()
//        .when()
//                .delete("/users/2")
//        .then()
//                .statusCode(204);
//    }
}