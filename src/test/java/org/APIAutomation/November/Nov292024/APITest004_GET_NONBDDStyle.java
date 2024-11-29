package org.APIAutomation.November.Nov292024;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest004_GET_NONBDDStyle {

    static RequestSpecification r = RestAssured.given();

    @Description("This is a test to validate the GET request-Non BDD STYLE")
    @Test

    public void test_GET_Req() {

        String pincode = "691522";

        r.baseUri("http://api.zippopotam.us");
        r.basePath("/IN/" + pincode);
        r.contentType(ContentType.JSON);
        r.when().get();
        r.then().statusCode(200).log().all();


    }
}
