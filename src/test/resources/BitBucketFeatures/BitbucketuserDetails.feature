@tag
Feature: validate onable Getuser  Details screen in Bitbucket
 
@GetUserDetails

  Scenario: verify the GetUser Details
    Given User enters into GetUser Details  with get request
    Then The API call got success with status code is 200