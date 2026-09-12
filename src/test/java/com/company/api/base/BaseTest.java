package com.company.api.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.company.api.utils.Log;

import org.testng.annotations.AfterMethod;

public class BaseTest {

    @BeforeClass
    public void setup() {
        Log.info("========== Starting Test Suite ==========");
        RestAssured.requestSpecification = SpecBuilder.getRequestSpec();
        RestAssured.responseSpecification = SpecBuilder.getResponseSpec();
    }

    @BeforeMethod
    public void beforeMethod(java.lang.reflect.Method method) {
        Log.info("Starting Test: " + method.getName());
    }

    @AfterMethod
    public void afterMethod(java.lang.reflect.Method method) {
        Log.info("Finished Test: " + method.getName());
    }
}