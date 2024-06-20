@EAModule
Feature: 	Testing the Get Organization Details Api

  Scenario Outline: Validate Organization Details
    Given Add Organization payload with Endpoint
    When User send Post request using get Organization Details Api "<GetOrgApi>"  <StatusCode>
    Then Validate the Response body from Get Organization Details
    
    Examples:
|                  GetOrgApi                  |  StatusCode |
|              /gitea/organization_details/ojas              |      200    |
