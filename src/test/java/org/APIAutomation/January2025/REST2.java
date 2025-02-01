package org.APIAutomation.January2025;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class REST2 {

    @Test
    public void testGETRequest() {


        RequestSpecification request = RestAssured.given();
        ValidatableResponse valResponse;
        Response response;


        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/booking");
        response =request.when().get();

        valResponse = response.then().statusCode(200);
        valResponse.log().all();

        String bookingId = response.jsonPath().getString("bookingid[0]");
        System.out.println("Booking ID is: " + bookingId);
    }

    @Test
    public void testPOSTBooking(){

        RequestSpecification request = RestAssured.given();
        ValidatableResponse validatableResponse;
        Response response;

        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/booking");
        request.contentType("application/json");
        request.cookie("token","abc123");
        request.body("{\n" +
                "    \"firstname\" : \"Liju\",\n" +
                "    \"lastname\" : \"Raju\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}");

        response = request.when().post();

        validatableResponse = response.then().statusCode(200);
        validatableResponse.log().all();

        String firstname = response.jsonPath().getString("booking.firstname");

        Assert.assertEquals(firstname,"Liju");
        System.out.println("Passed");
    }




}
