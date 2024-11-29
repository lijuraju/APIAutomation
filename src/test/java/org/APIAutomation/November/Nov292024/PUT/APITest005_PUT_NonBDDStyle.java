package org.APIAutomation.November.Nov292024.PUT;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest005_PUT_NonBDDStyle {

    //https://restful-booker.herokuapp.com/booking/:id
   // 'Content-Type: application/json'
    //Cookie: token=abc123
//    {
//        "firstname" : "James",
//            "lastname" : "Brown",
//            "totalprice" : 111,
//            "depositpaid" : true,
//            "bookingdates" : {
//        "checkin" : "2018-01-01",
//                "checkout" : "2019-01-01"
//    },
//        "additionalneeds" : "Breakfast"
//    }

    @Test  // This annotation helps Allure identify a test case
    @AllureId("001")  // Optional unique identifier
    @Description("This is a PUT request in NON BDD Style")
    @Feature("Booking")
    @Severity(SeverityLevel.CRITICAL)
    public void test_PUT_Req(){
        String payload = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        RequestSpecification r = RestAssured.given();
        r.baseUri("http://restful-booker.herokuapp.com");
        r.basePath("/booking/1");
        r.contentType("application/json");
        r.cookie("token=abc123");
        r.body(payload).log().all();
        r.when().put();
        r.log().all();
        r.then().log().all().statusCode( 200 );


    }

}
