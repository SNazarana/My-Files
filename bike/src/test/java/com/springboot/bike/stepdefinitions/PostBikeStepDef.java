package com.springboot.bike.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertTrue;

public class PostBikeStepDef {

    private String baseUrl;
    private Response response;
    private String Data;
    @Given("the base URL of the bike shop  is {string}")
    public void setBaseUrl(String url) {
        baseUrl = url;
    }
    @Given("add a new bike with following details:")
    public void setValidData(String validData) {
        Data = validData;
    }

    @When("the request is made to {string} with the details")
    public void makePostRequest(String endpoint) {
        response = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(Data)
                .post(endpoint);
    }
    @Then("the response status code should be {int}")
    public void checkStatusCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }
    @Then("the response body should contain the added bike details")
    public void checkResponseBodyForSuccess() {
        JsonPath responseBody = response.jsonPath();
        // Extracting values from the response body
        String bikeName = responseBody.get("name");
        String serialNumber = responseBody.get("serialnumber");
        Float purchasePrice = responseBody.get("purchaseprice");
        String purchaseDate = responseBody.get("purchasedate");
        // Extracting values from the payload
        JsonPath payloadJson = new JsonPath(Data);
        String expectedBikeName = payloadJson.get("name");
        String expectedPurchaseDate = payloadJson.get("purchasedate");
        Float  expectedPurchasePrice = payloadJson.get("purchaseprice");

        // Asserting values
        assertTrue("Bike name is not correct", bikeName.equals(expectedBikeName));
        assertTrue("Purchase date is not correct", purchaseDate.equals(expectedPurchaseDate));
        assertTrue("Purchase price is not null",purchasePrice.equals(expectedPurchasePrice));
    }

    @Then("the response body should contain the error message")
    public void checkResponseBodyForFailure() {
        String responseBody = response.getBody().asString();
        // Check if the response body contains the error message
        assertTrue("Error message not found in response body", responseBody.contains("error"));
    }
}
