package org.APIAutomation.November.Nov302024;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class APITest001_RESTAssuredBasics {

    static  RequestSpecification r = RestAssured.given();
    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";

    }

    @Test
    public void testGetRequest(){
        //Create a request specification


       Response resp =  r.get("/posts/1");


       //Assert the response status code and body
        resp.then().log().all();
        Assert.assertEquals(resp.getStatusCode(),200,"Statsus code should be 200");
        System.out.println("GET Resquest response body"+ resp.body().asString());

    }

    @Test
    public void testPOSTRequest(){

        r.contentType(ContentType.JSON);
        String payload = "{\n" +
                "    \"title\": \"foo\",\n" +
                "    \"body\": \"bar\",\n" +
                "    \"userId\": 1\n" +
                "}";

        Response resp = r.body(payload).post("/posts");
        r.then().log().all();
        Assert.assertEquals(resp.getStatusCode(),201,"Status code should be 10");
        System.out.println("POST Resquest response body"+ resp.body().asString());


    }

}
