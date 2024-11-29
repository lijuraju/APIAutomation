package org.APIAutomation.November.Nov272024;

import io.restassured.RestAssured;

public class APITest01 {

    public static void main(String[] args) {

        RestAssured
                .given()
                .baseUri("http://restful-booker.herokuapp.com")
                .basePath("/booking/2")
                .when()
                .get()
                .then().log().all()
                .statusCode(200);
    }
}
