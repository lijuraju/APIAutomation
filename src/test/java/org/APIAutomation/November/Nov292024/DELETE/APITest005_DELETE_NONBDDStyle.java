package org.APIAutomation.November.Nov292024.DELETE;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest005_DELETE_NONBDDStyle {

    //https://restful-booker.herokuapp.com/booking/1

    //Content-Type: application/json
    //Cookie: token=abc123

    @Test  // This annotation helps Allure identify a test case
    @AllureId("002")  // Optional unique identifier
    @Description("This is a Delete request in NON BDD Style")
    @Feature("Booking")
    @Severity(SeverityLevel.NORMAL)
    public void test_Delete_Req(){

        RequestSpecification r = RestAssured.given();

        r.baseUri("http://restful-booker.herokuapp.com");
        r.basePath("/booking/1");
        r.contentType(ContentType.JSON);
        r.cookie("token=abc123");
        r.log().all();
        r.when().delete();
        r.then().log().all().statusCode(201);

    }

}
