@GetOrganizationRepoDetailsNegitive

Feature: Testing GetOrganizationRepoDetails API In GitHub Module for Negitive Scenarios
 
  Scenario Outline: Get User Data From GetOrganizationRepoDetails API Negitive Scenarios
    Given I set That the API to The EndPoint of orgRepoAPI for negitive
    When I Send A GET Request to the orgRepoAPI for negitive "<GetOrgRepoAPI>"
    Then The Response Status Code of orgRepoAPI for negitive <StatusCode>
    And the response should be contain all user data of orgRepoAPI for negitive
    
    Examples: 
      | GetOrgRepoAPI               | StatusCode  |
     | /github                      |   404    |
     |/TinyGo                       |   404    |
       
       
     

