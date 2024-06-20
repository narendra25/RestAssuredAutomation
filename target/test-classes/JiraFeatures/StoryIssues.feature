
@StoryIssuesofSpring 
Feature: Testing StoryIssues of Spring Api in Jira module

 Scenario Outline: validate StoryIssues of Spring details
    Given add spring payload with "<sprintId>"
    When  user send Post request with StoryIssues of Spring api
    Then  Validate response body of StoryIssues of Spring deatails
    And   validate response headers of the StoryIssues of Spring  details
    
Examples:
| sprintId     |  
| 2            | 
  
