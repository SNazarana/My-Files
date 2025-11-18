Feature: Updating the bike details in Bike Shop Repository

  Background:
    Given  base URL of bike shop is "http://localhost:8080"

  @Success
  Scenario Outline: Update bike purchase price by ID
    Given a bike with ID <bikeId> exists in the database
    When the request is sent to "/bike/update" with the following details:
      """
      {
        "bikeId":"<bikeId>",
        "name": "<name>",
        "serialnumber": <serialnumber>,
        "purchaseprice": <purchaseprice>,
        "purchasedate": "<purchasedate>"
      }
      """
    Then the status code will be 200
    And the response body should contain the updated bike details validate the data in db

    Examples:
      | bikeId | name      | serialnumber | purchaseprice | purchasedate |
      | 2      | Yamaha R1 | 9876543210   | 500000.00     | 2023-12-31   |
      | 3      | Honda CBR | 2468135790   | 600000.00     | 2023-12-31   |

  @Failure
  Scenario Outline: Attempt to update bike purchase price with invalid data
    Given a bike with ID <bikeId> exists in the database
    When the request is sent to "/bike/update" with the following details:
     """
      {
        "bikeId":"<bikeId>",
        "name": "<name>",
        "serialnumber": <serialnumber>,
        "purchaseprice": <purchaseprice>,
        "purchasedate": "<purchasedate>"
      }
      """
    Then the status code will be 404
    And then check for the error message in response body

    Examples:
      | bikeId | name      | serialnumber | purchaseprice | purchasedate |
      | 8      | Yamaha R1 | 9876543210   | 500000.00     | 2023-12-31   |
      | 9      | Honda CBR | 2468135790   | 600000.00     | 2023-12-31   |
