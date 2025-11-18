Feature: Retriving a bike from Bike shop Repository

  Background:
    Given  Base URL of the bike shop is "http://localhost:8080"

  @Success
  Scenario Outline: Retrieve bike information by ID
    Given the id is "<bikeId>"
    When the request is sent to "/bike/<bikeId>"
    Then the response should contain the status code as 200
    And the response contains details of the bike
    Examples:
      | bikeId |
      | 3      |
      | 2      |

  @Failure
  Scenario Outline: Retrieve bike information by ID
    Given the id is "<bikeId>"
    When the request is sent to "/bike/<bikeId>"
    Then the response should contain the status code as 404
    And check the error message from response body
    Examples:
      | bikeId |
      | 9      |
      | 10     |
