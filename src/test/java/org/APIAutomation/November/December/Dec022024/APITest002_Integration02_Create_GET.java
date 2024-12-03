package org.APIAutomation.November.December.Dec022024;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest002_Integration02_Create_GET {

    RequestSpecification r = RestAssured.given();
    Response resp;
    ValidatableResponse vResp;
    String bookingID;
    String firstname;


    //https://restful-booker.herokuapp.com/booking

//    {
//        "firstname" : "Jim",
//            "lastname" : "Brown",
//            "totalprice" : 111,
//            "depositpaid" : true,
//            "bookingdates" : {
//        "checkin" : "2018-01-01",
//                "checkout" : "2019-01-01"
//    }

    //Content-Type: application/json


    @Description("This is a POST request,Non-BDD Style - Create Booking")
    @Test(priority = 1)
    public void test_POST_CreateBooking(){

        String payload = "{\n" +
                "    \"firstname\" : \"Reshma\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        r.baseUri("http://restful-booker.herokuapp.com");
        r.basePath("/booking");
        r.contentType(ContentType.JSON);
        r.cookie("token","abc123");
        r.body(payload).log().all();

        //When
        resp = r.when().post();

        //Then
        vResp = resp.then().statusCode(200).log().all();

        //To extract the booking ID
        bookingID = resp.jsonPath().getString("bookingid");
        System.out.println("My booking id is"+bookingID);


    }
    @Description("This is a GET request,Non-BDD Style - GET Booking details for an ID")
    @Test(priority = 2)
    public void test_GET_GETBooking(){

        r.baseUri("http://restful-booker.herokuapp.com");
        r.basePath("/booking/" + bookingID);
        r.contentType(ContentType.JSON);

        //When
        resp = r.when().get();

        //Then
        vResp = resp.then().statusCode(200).log().all();

        //To extract the firstname
       firstname = resp.jsonPath().getString("firstname");

        //Assertion

        Assert.assertEquals(firstname,"Reshma");




    }


}
