package com.springboot.bike.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.sql.*;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;


public class PutStepDef {
     ResultSet resultSet=null;
    private Response response;
    private String Data;

    @Given("base URL of bike shop is {string}")
    public void setBaseUrl(String url) {
        RestAssured.baseURI = url;
    }

    @Given("a bike with ID {long} exists in the database")
    public void bikeExistsInDatabase(long bikeId) {
        // Implement logic to ensure that the bike exists in the database
    }

    @When("the request is sent to {string} with the following details:")
    public void sendPutRequest(String endpoint, String requestBody) {
        response = given().contentType(ContentType.JSON).body(requestBody).log().body().when().put(endpoint);
        System.out.println(response.getStatusCode());
    }

    @Then("the status code will be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }


    @And("the response body should contain the updated bike details validate the data in db")
    public void validate_the_data_in_DB() {
        try{
        assertNotNull(response);
        System.out.println(response.getBody().asString());

//        String url = "jdbc:postgresql://localhost:5432/bikes";
//        String user = "postgres";
//        String password = "Sheik98";

//        try (Connection connection = DriverManager.getConnection(url, user, password);)

            //get data from response body
            Long id = response.jsonPath().getLong("bikeId");
            System.out.println(id);
            String name= response.jsonPath().getString("name");
            System.out.println(name);
            String serialnumber= response.jsonPath().getString("serialnumber");
            System.out.println(serialnumber);
            String purchasedate= response.jsonPath().getString("purchasedate");
            System.out.println(purchasedate);
            Float purchaseprice= response.jsonPath().getFloat("purchaseprice");
            System.out.println(purchaseprice);
            try{


                 Hooks.statement = Hooks.connection.createStatement();
            //get data from db
            String sql = "SELECT * FROM bikes WHERE bike_id = " + id;
                 System.out.println(sql);
            //PreparedStatement statement = connection.prepareStatement(sql);
            //ResultSet resultSet = statement.executeQuery(sql);

               resultSet = Hooks.statement.executeQuery(sql);
            System.out.println(sql);
            System.out.println((resultSet));}
            catch(SQLException e){
                throw new RuntimeException();
               // System.out.println("Requested fields does not exists in response");

              }
            if(resultSet.next())

            {
                System.out.println("connection created");
                Long bikeid =resultSet.getLong("bike_id");
                System.out.println(bikeid);
                String bikename= resultSet.getString("name");
                String bikeserialnumber= resultSet.getString("serialnumber");
                String bikepurchasedate= resultSet.getString("purchasedate");
                Float bikepurchaseprice= resultSet.getFloat("purchaseprice");
                assertEquals(bikeid,id,"bike id did not match");
                assertEquals(bikename,name,"bikename did not match");
                assertEquals(bikeserialnumber,serialnumber,"serialnumber did not match");
                assertEquals(bikepurchasedate,purchasedate,"purchasedate did not match");
                assertEquals(bikepurchaseprice,purchaseprice,"purchaseprice did not match");
            }else {

            }
        } catch (Exception e) {
            System.out.println("Requested fields does not exists in response");

        }
    }
    @And("then check for the error message in response body")
    public void checkResponseBodyForFailure() {
        String responseBody = response.getBody().asString();
        // Check if the response body contains the error message
        assertTrue("Error message not found in response body", responseBody.contains("There is no such bike found"));
    }
}


