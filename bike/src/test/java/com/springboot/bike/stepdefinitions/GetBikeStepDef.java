package com.springboot.bike.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class GetBikeStepDef {
    private Long bikeID;
    private String Data;
    private String baseURL;
    private String bikeId;
    private Response response;

    @Given("Base URL of the bike shop is {string}")
    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    @When("the request is sent to {string}")
    public void sendGetRequest(String endpoint) {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    @Given("the id is {string}")
    public void the_id_is(String id) {
        bikeID = Long.parseLong(id);
    }
    @Then("the response should contain the status code as {int}")
    public void checkStatusCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @And("the response contains details of the bike")
    public void the_response_should_contain_the_bike_details() {
        String responseBody = response.getBody().asString();
        // Perform assertions to check if response body contains details of the bike
        assertTrue(responseBody.contains("bikeId"));
        assertTrue(responseBody.contains("name"));
        assertTrue(responseBody.contains("serialnumber"));
        assertTrue(responseBody.contains("purchasedate"));
        assertTrue(responseBody.contains("purchaseprice"));
    }
    @And("check the error message from response body")
    public void checkResponseBodyForFailure() {
        String responseBody = response.getBody().asString();
        // Check if the response body contains the error message
        assertTrue("Error message not found in response body", responseBody.contains("There is no such bike found with this id"));
    }
    }










