package org.APIAutomation.Apr2025.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class StepDef2 {

    RequestSpecification request = RestAssured.given();
    Response response;
    ValidatableResponse valResponse;

    @When("user send a GET request")
    public void user_send_a_get_request() {

        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/booking");
        request.contentType("application/json");
        response = request.when().get();
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer int1) {
        valResponse = response.then().
                statusCode(int1).header("Content-Type", "application/json; charset=utf-8")
                .log().all();
        Assert.assertEquals(response.getStatusCode(),200,"Status code should be 200");
        //System.out.println("GET Resquest response body"+ response.body().asString());
    }

    @When("user send a GET request with id {string}")
    public void userSendAGETRequestWithId(String id) {

        request.baseUri("http://restful-booker.herokuapp.com");
        request.basePath("/booking/" + id);
        request.contentType("application/json");
        response = request.when().get();

    }

    @And("the response body should contain the first name {string}")
    public void theResponseBodyShouldContainTheFirstName(String name) {

        String fname = response.jsonPath().getString("firstname");
        Assert.assertEquals(fname,"Jim","First name should be Jim");

    }

    @And("the response body should contain totalprice {string}")
    public void theResponseBodyShouldContainTotalprice(String price) {

        String totalPrice = response.jsonPath().getString("totalprice");
        Assert.assertEquals(totalPrice,"638","Total price should be 638");

    }

    @And("the response body should contain checkindate {string}")
    public void theResponseBodyShouldContainCheckindate(String date) {

            String checkinDate = response.jsonPath().getString("bookingdates.checkin");
            Assert.assertEquals(checkinDate,"2018-07-01","Checkin date should be 2018-07-01");
    }
}
