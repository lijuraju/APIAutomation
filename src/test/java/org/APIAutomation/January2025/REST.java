package org.APIAutomation.January2025;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThan;

public class REST {

    private static final String BASE_URL = "http://restful-booker.herokuapp.com";

    @Test
    public void testGETRequestBooking(){

//        RequestSpecification request = RestAssured.given();
//        ResponseSpecification responseSpec ;
//        ValidatableResponse valResponse;
//        Response response3;
//
//        request.baseUri(BASE_URL);
//        request.basePath("/booking");
//
//        response3 = request.when().get();
//        valResponse = response3.then().statusCode(200);
//        valResponse.log().all();

                // Define RequestSpecification
                RequestSpecification requestSpec = new RequestSpecBuilder()
                        .setBaseUri(BASE_URL)
                        .setBasePath("/booking")
                        .build();

                // Define ResponseSpecification
                ResponseSpecification responseSpec = new ResponseSpecBuilder()
                        .expectStatusCode(200) // Expect status code 200
                        .expectContentType("application/json") // Expect JSON response
                        .expectBody("size()", greaterThan(0))
                        .build();

                // Send GET request and validate using ResponseSpecification
                ValidatableResponse valResponse = RestAssured
                        .given()
                        .spec(requestSpec) // Apply RequestSpecification
                        .when()
                        .get()
                        .then()
                        .spec(responseSpec); // Apply ResponseSpecification

                // Log the response for debugging
                valResponse.log().all();
            }
        }




