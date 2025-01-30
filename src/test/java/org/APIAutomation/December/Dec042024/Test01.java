package org.APIAutomation.December.Dec042024;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test01 {


        RequestSpecification request = RestAssured.given();
        String token;
        String booking_id;
        Response response;
        ValidatableResponse validateresponse;

        public String GET_TOKEN(){
            String payload = "{\n" +
                    "       \"username\" : \"admin\",\n" +
                    "       \"password\" : \"password123\"\n" +
                    "}";
            String token;

            request.baseUri("http://restful-booker.herokuapp.com");
            request.basePath("/booking");
            request.contentType(ContentType.JSON);
            request.body(payload).log().all();

            //when - saving the response
            response = request.when().post();

            //then - validate the response with the statuscode
            validateresponse = response.then().statusCode(200);

            //extract the token
            token = response.then().extract().path("token");
            System.out.println(token);
            return token;
        }

        public String GET_BOOKINGID(){
            String payload = "{\n" +
                    "    \"firstname\" : \"Sindhuja\",\n" +
                    "    \"lastname\" : \"Jayaraman\",\n" +
                    "    \"totalprice\" : 111,\n" +
                    "    \"depositpaid\" : true,\n" +
                    "    \"bookingdates\" : {\n" +
                    "        \"checkin\" : \"2018-01-01\",\n" +
                    "        \"checkout\" : \"2019-01-01\"\n" +
                    "    },\n" +
                    "    \"additionalneeds\" : \"{{additionalneeds}}\"\n" +
                    "}";

            request.baseUri("http://restful-booker.herokuapp.com");
            request.basePath("/booking");
            request.contentType(ContentType.JSON);
            request.body(payload).log().all();

            //when - saving the response
            response = request.when().post();

            //then - validate the response with the statuscode
            validateresponse = response.then().statusCode(200);

            //extractbookingid
            booking_id = response.then().extract().path("bookingid");
            System.out.println(booking_id);
            return booking_id;
        }

        @Test(priority = 1)
        public void PUT_REQUEST() {
            String payload = "{\n" +
                    "    \"firstname\" : \"Varnika\",\n" +
                    "    \"lastname\" : \"Santhosh\",\n" +
                    "    \"totalprice\" : 112,\n" +
                    "    \"depositpaid\" : true,\n" +
                    "    \"bookingdates\" : {\n" +
                    "        \"checkin\" : \"2024-01-01\",\n" +
                    "        \"checkout\" : \"2025-01-01\"\n" +
                    "    },\n" +
                    "    \"additionalneeds\" : \"{{additionalneeds}}\"\n" +
                    "}";

            request.baseUri("http://restful-booker.herokuapp.com");
            request.basePath("/booking/" + booking_id);
            request.contentType(ContentType.JSON);
            request.cookie("token", token);
            request.body(payload).log().all();

            //when -
            response = request.when().put();

            //then -
            validateresponse = response.then().statusCode(200).log().all();

        }
        @Test(priority = 2)
        public void GET_REQUEST_AFTERPUT(){
            String firstname;
            String lastname;

            request.baseUri("http://restful-booker.herokuapp.com");
            request.basePath("/booking/"+booking_id);
            request.contentType(ContentType.JSON);
            request.log().all();

            //when -
            response = request.when().put();

            //then -
            validateresponse = response.then().statusCode(200).log().all();
            firstname = response.jsonPath().getString("firstname");
            Assert.assertEquals(firstname,"Varnika");
            lastname = response.jsonPath().getString("lastname");
            Assert.assertEquals(lastname,"Santhosh");

        }

        @Test(priority = 3)
        public void DELETE_REQUEST(){
            request.baseUri("http://restful-booker.herokuapp.com");
            request.basePath("/booking/"+booking_id);
            request.contentType(ContentType.JSON);
            request.log().all();

            //when -
            response = request.when().delete();

            //then -
            validateresponse = response.then().statusCode(201).log().all();
        }
    }

