Feature: 	Testing the Get Repo Details GIT Api for Negitive Cases
 
  Scenario Outline: Validate Get Repo Details GIT for Negitive Cases
    Given Add Get Repo GIT payload with Endpoint for API
    When User send Post request using get Repo Details GIT Api "<GetOrgApi>"  <StatusCode>
    Then Validate the Response body from Get Repo Details GIT for Negitive
    
     Examples:
      | GetOrgRepoAPI                              | StatusCode  |
      | /github/repos                              |   200       |
      | /github/TinyGo/repos                       |   500       |  

 