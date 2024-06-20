@GetBranches
Feature: Testing the Get Branches Details Api 

  Scenario Outline: Validate Branches details                
    Given Add Branches payload with Endpoint
    When User send Post request using getBranchesDetails Api "<GetOrgApi>"  <StatusCode>
    Then Validate the Response body from Get Branches Details
    
    Examples:
|                  GetOrgApi                  |  StatusCode |
| /gitea/branches/ojas/oneable-productivity |      200    |


