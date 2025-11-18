package com.springboot.bike.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.sql.*;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertTrue;


public class DeleteStepDef {
    private Connection connection;
    private String baseUrl;
    private Response response;
    @Given("the base URL of the bike  is {string}")
    public void setBaseUrl(String url) {
        baseUrl = url;
    }

    @Given("the bike is created in database")
    public void createBikeInDatabase() {
        // Define the PostgreSQL connection URL
        String url = "jdbc:postgresql://localhost:5432/bikes";
        String user = "postgres";
        String password = "Sheik98";

        // Define the SQL query to insert a bike
        String sql = "INSERT INTO bikes (bike_id, name, serialnumber, purchaseprice, purchasedate) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the parameters for the SQL query (hardcoded values)
            statement.setLong(1, Long.parseLong("4"));
            statement.setString(2, "Kawasaki Ninja");
            statement.setString(3, "1234567890");
            statement.setDouble(4, 350000.00);
            statement.setString(5, "2023-05-25");

            // Execute the SQL query to insert the bike
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new bike was inserted successfully.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while inserting the bike.");
            e.printStackTrace();
        }
    }

    @When("the  request is sent to {string}")
    public void sendDeleteRequest(String endpoint) {
        response = given().delete(String.valueOf(endpoint));
    }

    @Then("the response status is {int}")
    public void checkStatusCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
        System.out.println(expectedStatusCode);
    }

    @And("the user is successfully deleted and validate message Entity deleted")
    public void validateSuccessfulDeletionMessage() {
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @And("then check the error message in response")
    public void checkResponseBodyForFailure() {
        String responseBody = response.getBody().asString();
        // Check if the response body contains the error message
        assertTrue("Error message not found in response body", responseBody.contains("There is no such bike found"));
    }

}
