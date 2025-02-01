package org.APIAutomation.February2025.Feb012025;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.APIAutomation.utility.JsonUtility;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IntegrationScenario {

    RequestSpecification request = RestAssured.given();
    Response response;
    ValidatableResponse valResponse;
    String token;
    String bookingID;

    @Test(priority = 1)
    public void testCreateToken() {

        String filePath = "src/test/resources/Auth.json";
        JSONObject jsonObject = JsonUtility.readJsonFile(filePath);

        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/auth");
        request.contentType(ContentType.JSON);
        request.body(jsonObject.toString());

        response = request.when().post();
        valResponse = response.then().statusCode(200);
        token = response.jsonPath().getString("token");
        valResponse.log().all();
        System.out.println("TC1 Success");

    }

    @Test(priority = 2)
    public void testCreateBooking() {

        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/booking");
        request.contentType(ContentType.JSON);
        request.cookie("token", token);
        request.body("{\n" +
                "    \"firstname\" : \"Reshma\",\n" +
                "    \"lastname\" : \"Mathew\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}");

        response = request.when().post();
        valResponse = response.then().statusCode(200);
        valResponse.log().all();
        String firstname = response.jsonPath().getString("booking.firstname");
        String lastname = response.jsonPath().getString("booking.lastname");
        bookingID = response.jsonPath().getString("bookingid");


        Assert.assertEquals("Reshma", firstname);
        Assert.assertEquals("Mathew", lastname);
        System.out.println("TC2");

    }

    @Test(priority = 3)
    public void testUpdateBooking() {

        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/booking/" + bookingID);
        request.contentType(ContentType.JSON);
        request.cookie("token", token);
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

        response = request.when().put();
        valResponse = response.then().statusCode(200);
        valResponse.log().all();
        bookingID = response.jsonPath().getString("bookingid");
        System.out.println("TC3");
        //return bookingID;

    }

    @Test(priority = 4)
    public void testGetBookingID() {

        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/booking/" + bookingID);
        request.contentType(ContentType.JSON);

        response = request.when().get();
        valResponse = response.then().statusCode(200);
        valResponse.log().all();
        String firstname = response.jsonPath().getString("firstname");
        Assert.assertEquals("Liju", firstname);
        System.out.println("TC4");
    }

    @Test(priority = 5)
    public void testPATCHRequest() {
        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/booking/" + bookingID);
        request.contentType(ContentType.JSON);
        request.cookie("token", token);
        request.body("{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\"\n" +
                "}");

        response = request.when().patch();
        valResponse = response.then().statusCode(200);
        valResponse.log().all();
        String firstname = response.jsonPath().getString("firstname");
        Assert.assertEquals("James", firstname);
        System.out.println("TC5 Success");

    }
}
