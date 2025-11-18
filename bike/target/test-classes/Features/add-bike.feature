Feature: Creating Bike SHop Request

  Background:
    Given the base URL of the bike shop  is "http://localhost:8080"

  @Success
  Scenario Outline: Add a new bike to the inventory
    Given add a new bike with following details:
      """
      {
        "bikeId":"<bikeId>",
        "name": "<name>",
        "serialnumber": <serialnumber>,
        "purchaseprice": <purchaseprice>,
        "purchasedate": "<purchasedate>"
      }
      """
    When the request is made to "/bike/add" with the details
    Then the response status code should be <expectedStatusCode>
    And the response body should contain the added bike details
    Examples:
      | bikeId | name           | serialnumber | purchaseprice | purchasedate    | expectedStatusCode |
      | 1      | Kawasaki Ninja | 1234567890   | 350000.00        | 2023-05-25   | 201                |
      | 2      | Yamaha R1      | 9876543210   | 400000.00        | 2023-12-31   | 201                |
      | 3      | Honda CBR      | 2468135790   | 300000.00        | 2023-12-31   | 201                |

  @Failure
  Scenario Outline: Add a new bike with invalid data
    Given add a new bike with following details:
      """
      {
      "bikeId":"<bikeId>",
        "bikeName": "<name>",
        "serialNumber": <serialnumber>,
        "purchasePrice": <purchaseprice>,
        "purchaseDate": "<purchasedate>"
      }
      """
    When the request is made to "/bike/add" with the details
    Then the response status code should be <expectedStatusCode>
    And the response body should contain the error message
    Examples:
      | bikeId | name             | serialnumber | purchaseprice | purchasedate | expectedStatusCode |
      | 2      | " "              | 987654321    | 350000.00        | "2023-12-31" | 400                |
      | 3      | "Kawasaki Ninja" | 987654321    | 350000.00        | "2023-12-31" | 400                |
      | 4      | "Kawasaki Ninja" | " "          | 350000.00        | "2023-12-31" | 400                |
      | 5      | "Kawasaki Ninja" | 987654321    | -350000.00       | "2023-12-31" | 400                |
      | 6      | "Kawasaki Ninja" | 987654321    | 350000.00        | "2025-12-31" | 400                |
      | 7      | "Kawasaki Ninja" | 987654321    | 350000.00        | " "          | 400                |