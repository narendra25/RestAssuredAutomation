@tag
Feature: validate onable GetBranches  Details screen in Bitbucket

@GetBranchesDetails
  Scenario: verify the GetBranches Details
    Given User enters into GetBranches Details  with get request
    Then The API call got success with status code is 200