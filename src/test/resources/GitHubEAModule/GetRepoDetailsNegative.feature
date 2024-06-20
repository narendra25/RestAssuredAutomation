@Negitive
Feature: 	Testing the Get Repository Details Api for Negitive Cases

  Scenario Outline: Validate Repository Details for Negitive Cases
    Given Add Repository payload with Endpoint for API
    When User send Post request using get Repository Details Api "<GetOrgApi>"  "<OrganizationName>" 
    Then Validate the Response body from Get Repository Details for Negitive
    
    Examples:
|                  GetOrgApi                             | OrganizationName| 
|  /gitea/repository/{OrganizationName}                  |   Vinay Kumar    |
| /gitea/repository/{OrganizationName}                   |  ojas               |

