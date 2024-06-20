@EAModule
Feature: Testing the Get Repository Details Api 

  Scenario Outline: Validate organization details                
    Given Add Repository payload with Endpoint
    When User send Post request using get Repository Details Api "<GetOrgApi>"  <StatusCode>
    Then Validate the Response body from Get Repository Details
    
    Examples:
|                  GetOrgApi                  |  StatusCode |
| /gitea/repository/ojas/oneable-productivity |      200    |


