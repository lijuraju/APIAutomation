package org.APIAutomation.Apr2025;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class Task1 {
    public static void main(String[] args) {

        //BDD style
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .when()
                .get()
                .then().log().all()
                .statusCode(200);



    }
}
