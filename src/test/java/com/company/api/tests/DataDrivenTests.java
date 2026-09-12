package com.company.api.tests;

import com.company.api.base.BaseTest;
import com.company.api.pojos.LoginRequest;
import com.company.api.pojos.UserRequest;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DataDrivenTests extends BaseTest {

    @DataProvider(name = "userData")
    public Object[][] userDataProvider() {
        return new Object[][] {
                {"Morpheus", "Leader"},
                {"Neo", "The One"},
                {"Trinity", "Hacker"}
        };
    }

    @Test(dataProvider = "userData")
    @Description("Create multiple users using DataProvider")
    public void createUserDataDriven(String name, String job) {
        UserRequest request = new UserRequest(name, job);

        given()
                .body(request)
        .when()
                .post("/users")
        .then()
                .statusCode(201)
                .body("name", equalTo(name))
                .body("job", equalTo(job))
                .body("id", notNullValue());
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"eve.holt@reqres.in", "cityslicka", 200},
                {"peter@klaven", "", 400}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginDataDriven(String email, String password, int expectedStatus) {
    	LoginRequest request = new LoginRequest(email,password);
        given()
                .body(request)
        .when()
                .post("/login")
        .then()
                .statusCode(expectedStatus);
    }
}