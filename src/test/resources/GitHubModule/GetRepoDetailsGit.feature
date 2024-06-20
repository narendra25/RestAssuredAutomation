@GetRepoDetails

Feature: Testing GetRepoDetails API In GitHub Module
 
  Scenario Outline: Get User Data From GetRepoDetails API
    Given I set That the API to The EndPoint of GetRepoDetails API 
    When I Send A GET Request to the GetRepoDetails API "<GetOrgRepoAPI>"
    Then The Response Status Code of GetRepoDetails API <StatusCode>
    And the response should be contain all user data of GetRepoDetails API
    
    Examples: 
      | GetOrgRepoAPI                     | StatusCode  |
      | /github/TinyGo/repos              |   200       |
        ###| /github/Tin                  |   500       |  
       
     

