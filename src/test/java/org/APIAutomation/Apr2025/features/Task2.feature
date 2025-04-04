Feature: Get User Details API

Scenario: Retrieve booking details successfully
When user send a GET request
Then the response status code should be 200
#And the response body should contain the user's name "John Doe"
#And the response body should contain the user's email "john.doe@example.com"

  Scenario: Retrieve booking for a specific id
    When user send a GET request with id "1"
    Then the response status code should be 200
    And the response body should contain the first name "Jim"
    And the response body should contain totalprice "638"
    And the response body should contain checkindate "2018-07-01"

