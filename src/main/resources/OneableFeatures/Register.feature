@register
Feature: validate oneable Registration
  
  Scenario: Verify the user is sucessfully added by using Register API
    Given Add Payload
    When User registers RegisterAPI with post http request
    Then The API call got success with status code 200
    And "Message" in response body is "user registred successfully with username : "Deepthi"
    And "data" in response body is "Deepthi"

 