@GetBranchesDetailNegitive
Feature: Testing the Get Branches Details Api Negitive Scenarios

  Scenario Outline: Validate Branches details Negitive Scenarios              
    Given Add Branches payload with Endpoint of Negitive Scenario
    When User send Post request using getBranchesDetails Api Negitive Scenario "<GetOrgApi>"  <StatusCode>
    Then Validate the Response body from Get Branches Details Negitive Scenario
    
    Examples:
|                  GetOrgApi                  |  StatusCode |
| /gitea/branches/ojas/oneable                |      404   |
| /gitea/branches/ojas/productivity              |      404   |
| /gitea/branches/ojas/ojs,oneable-productivity              |      404   |


