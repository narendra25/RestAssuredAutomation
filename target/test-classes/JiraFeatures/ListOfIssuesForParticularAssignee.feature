
@ListofIssuesforAssignee 
Feature: Testing ListofIssues for Assignee Api in Jira module

 Scenario Outline: validate ListofIssues for Assignee details
    Given add the assignee payload with "<assigneeAccountId>"
    When  user send Post request with ListofIssues for Assignee api
    Then  Validate response body of ListofIssues for Assignee deatails
    And   validate response headers of the ListofIssues for Assignee details
    
Examples:
| assigneeAccountId    |  
| 62d64f5a04a004286c0277d1 |
  
