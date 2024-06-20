@login
Feature: Title of your feature
  I want to use this template for my feature file

  Scenario Outline: validate login token
    Given user payload login with "<username>" "<password>"
    When user logins loginApi with post http request
    Then "message" in the response body is "Token token generated successfully for user : Navyadeepthi"
    And  accesstoken validation in the response body
    Examples: 
      | username     | password | 
      | Navyadeepthi | Ojas@12345 | 
      
