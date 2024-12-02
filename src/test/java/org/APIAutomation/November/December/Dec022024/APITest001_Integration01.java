package org.APIAutomation.November.December.Dec022024;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest001_Integration01 {

    RequestSpecification request = RestAssured.given();
    Response response;
    ValidatableResponse valResponse;
    String bookingID;
    String token;

    public String getToken() {

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        String token;

        //Given
        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/auth");
        request.contentType(ContentType.JSON);
        request.body(payload);

        //When
        response = request.when().post();

        //Then
        valResponse = response.then().statusCode(200);

        //Extracting the token

        token = response.jsonPath().getString("token");

        System.out.println(token);
        return token;

    }

    public String getBookingID() {

        String payload = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        String bookingID;

        //Given
        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/booking");
        request.contentType(ContentType.JSON);
        request.body(payload);

        //When
        response = request.when().post();

        //Then
        valResponse = response.then().statusCode(200);

        //Extracting the booking ID

        bookingID = response.jsonPath().getString("bookingid");
        System.out.println(bookingID);
        return bookingID;


    }

    @Test(priority = 1)
    public void test_PUT_Req() {

        bookingID = getBookingID();
       token = getToken();

        String payload = "{\n" +
                "    \"firstname\" : \"Liju\",\n" +
                "    \"lastname\" : \"Raju\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        //Given
        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/booking/" + bookingID);
        request.contentType(ContentType.JSON);
        request.cookie("token", token);
        request.body(payload);

        //When
        response = request.when().put();

        //Then
        valResponse = response.then().statusCode(200);


    }

    @Test(priority = 2)
    public void test_GET_Req() {


        //Given
        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/booking/" + bookingID);
        request.contentType(ContentType.JSON);


        //When
        response = request.when().put();

        //Then
        valResponse = response.then().statusCode(200);

        //assertion
        String firstname = response.jsonPath().getString("firstname");
        Assert.assertEquals(firstname, "Liju");
    }

    @Test(priority = 3)
    public void test_DELETE_Req() {


        //Given
        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/booking/" + bookingID);
        request.contentType(ContentType.JSON);

        //When
        response = request.when().delete();

        //Then
        valResponse = response.then().statusCode(201);


    }
}

