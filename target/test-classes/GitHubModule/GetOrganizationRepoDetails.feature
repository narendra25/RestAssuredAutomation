@GetOrganizationRepoDetails

Feature: Testing GetOrganizationRepoDetails API In GitHub Module
 
  Scenario Outline: Get User Data From GetOrganizationRepoDetails API
    Given I set That the API to The EndPoint of orgRepoAPI 
    When I Send A GET Request to the orgRepoAPI "<GetOrgRepoAPI>"
    Then The Response Status Code of orgRepoAPI <StatusCode>
    And the response should be contain all user data of orgRepoAPI
    
    Examples: 
      | GetOrgRepoAPI               | StatusCode  |
      | /github/TinyGo              |   200     |
       
       
     

