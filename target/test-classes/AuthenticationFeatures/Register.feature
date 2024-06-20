@register
Feature: validate oneable Registration
  
Scenario Outline: Verify the user is sucessfully added by using Register API
    Given Add Payload with "<username>" "<password>"
    When User registers RegisterAPI with post http request
    Then The API call got success with status code 200
    And "Message" in response body is "user registred successfully with username : Navyadeethi "
    And "data" in response body is "Navyadeepthi"
    
Examples:
|username     | password    |  
|Navyadeepthi | Ojas@12345   |
 