package org.APIAutomation.November.Nov292024;


import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITest001_BDDStyleGET {

@Test
    public void test_GET_Req_Positive() {

    String pin_code = "691522";
    RestAssured
            .given()
            .baseUri("http://api.zippopotam.us")
            .basePath("/IN/" + pin_code)
            .when()
            .log().all()
            .get()
            .then()
            .log().all()
            .statusCode(200);
}

@Test
    public void test_GET_Req_Negative(){

    String pin_code = "-1";

    RestAssured
            .given()
            .baseUri("http://api.zippopotam.us")
            .basePath("/IN/" + pin_code)
            .when()
            .get()
            .then()
            .log().all()
            .statusCode(404);
}
}
