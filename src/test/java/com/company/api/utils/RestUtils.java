package com.company.api.utils;

import io.restassured.response.Response;

public class RestUtils {

    public static void printResponse(Response response) {
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
    }
}