Feature: Bike Shop Repository-Delete a bike from the database

  Background:
    Given the base URL of the bike  is "http://localhost:8080"

  @Success
  Scenario Outline: Delete a specific bike with valid bikeId
    Given the bike is created in database
    When the  request is sent to "/bike/<bikeId>"
    Then the response status is 200
    And the user is successfully deleted and validate message Entity deleted
    Examples:
      |bikeId|
      |4     |

  @Failure
  Scenario Outline: Delete a bike with Invalid bikeId
    Given the bike is created in database
    When the  request is sent to "/bike/<bikeId>"
    Then the response status is 404
    And then check the error message in response
    Examples:
      |bikeId|
      |5     |
