@tag
Feature: Testing Sprint details Api in Jira module
 @sprintdetails
 Scenario Outline: validate sprint details
    Given add sprint payload with "<sprintId>"
    When  user send Post request with sprintdetails api
    Then  Validate response body of sprint deatails
    And   validate response headers of the sprint details
    
Examples:
| sprintId     |  
| 1            | 
  
 


 

  