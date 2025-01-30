package org.APIAutomation.December.Dec022024;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest003_Integration03_GET_PUT {

    RequestSpecification r = RestAssured.given();
    Response resp ;
    ValidatableResponse vResp;
    String bookingID;

    @Description("This is a GET Request - Non BDD Style - For getting Booking ID")
    @Test(priority = 1)
    public void test_GET_BookingID(){

        r.baseUri("http://restful-booker.herokuapp.com");
        r.basePath("/booking");
        r.log().all();

        //When
        resp = r.when().get();

        //Then
        vResp = resp.then().statusCode(200);
        vResp.log().all();

        //for getting booking id
        bookingID = resp.jsonPath().getString("[0].bookingid");
        System.out.println(bookingID);

    }
    @Test(priority = 2)
    public void test_PUT_Req() {

        String payload = "{\n" +
                "    \"firstname\" : \"Abram\",\n" +
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
        r.baseUri("http://restful-booker.herokuapp.com");
        r.basePath("/booking/" + bookingID);
        r.contentType(ContentType.JSON);
        r.body(payload);
        r.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=");

        //When
        resp = r.when().put();

        //Then
        vResp = resp.then().statusCode(200);

        //Assertion
        String firstName = resp.jsonPath().getString("firstname");
        Assert.assertEquals(firstName,"Abram");
        System.out.println("Successfully validated");


    }



}
