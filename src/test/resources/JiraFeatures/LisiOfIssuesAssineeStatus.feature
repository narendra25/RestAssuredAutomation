
@ListOfIssuesAssineeStatus
Feature: Testing ListOfIssuesAssineeStatus Api in Jira module

 Scenario Outline: validate ListOfIssuesAssineeStatus
    Given add the assigne payload with "<assigneeAccountId>" "<userStatus>"
    When  user send Post request with ListOfIssuesAssineeStatus api
    Then  Validate response body of ListOfIssuesAssineeStatus
     And   validate response headers of the ListOfIssuesAssineeStatus
    
Examples:
| assigneeAccountId        |  userStatus | 
| 62d64f5a04a004286c0277d1 |  DONE       |
  