@GetUserDetails @Regression
Feature: Testing GetUserDetails API In GitHub EA Module
 
  Scenario Outline: Get User Data From GetUserDetail API
    Given I set The API to The EndPoint 
    When I Send A GET Request to the GetUserDetail API 
    Then The Response Status Code of GetUserDetail API 
    And the response should be contain user data of GetUserDetail API 
     
       
     