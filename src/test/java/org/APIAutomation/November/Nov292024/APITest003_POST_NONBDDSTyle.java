package org.APIAutomation.November.Nov292024;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class APITest003_POST_NONBDDSTyle {


    @Description("This is a POST Request - NON BDD STYLE of way")
    @Test

    public void test_Post_Req(){

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        RequestSpecification r = RestAssured.given();
        r.baseUri("http://restful-booker.herokuapp.com/");
        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.body(payload);
        r.log().all();
        r.when().post();
        r.then().statusCode(200).log().all();


    }
}
