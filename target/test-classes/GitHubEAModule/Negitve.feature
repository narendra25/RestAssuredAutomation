@Negitive
Feature: 	Testing the Get Organization Details Api for Negitive Cases

  Scenario Outline: Validate Organization Details for Negitive Cases
    Given Add Organization payload with Endpoint for API
    When User send Post request using get Organization Details Api "<GetOrgApi>"  "<OrganizationName>" 
    Then Validate the Response body from Get Organization Details for Negitive
    
    Examples:
|                  GetOrgApi                      | OrganizationName| 
| /gitea/organization_details/{OrganizationName}   |   Vinay Kumar    |
| /gitea/organization_details/{OrganizationName}   |  ojas               |

