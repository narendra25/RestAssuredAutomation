@GetBranchDetails @Regression
Feature: Testing GetBranchDetails API In GitHub EA Module
 
  Scenario Outline: Get Branch Data From GetBranchDetail API
    Given I set The API to The EndPoint of GetBranchDetails
    When I Send A GET Request to the GetBranchDetails API 
    Then The Response Status Code of GetBranchDetails API 
    And the response should be contain user data of GetBranchDetails API 
     
       