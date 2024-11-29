package org.APIAutomation.November.Nov292024;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class APITest002_POST_BDDSTYLE {

    // https://restful-booker.herokuapp.com/auth

    // Content-Type: application/json

    // {
    //    "username" : "admin",
    //    "password" : "password123"
    //}
    @Description("This is a POST Request - BDD STYLE of way")
    @Test
    public void test_Post_Req() {

        String payload ="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        RestAssured
                .given()
                .baseUri("http://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .body(payload)
                .log().all()
                .when().post()
                .then()
                .log().all()
                .statusCode(200);
    }

}
