@GetOrganizationRepoDeatilsBitBucketNegitiveCases\

Feature: Verify the Get Organization Repo Details For Negitive Scenarios

  Scenario Outline: Validate Get Organization Repo Details For Negitive Data          
    Given Add Get Organization Repo Details with Endpoint For Negitive Data 
    When User send GET request using Get Organization Repo Details API For Negitive  "<GetOrganizationRepoDetailsAPI>" "<OrganizationName>" "<RepositoryName>" <StatusCode>
    Then Validate the Response body from Get Organization Repo Details Scenario For Negitive Data 
    
     Examples:
| GetOrganizationRepoDetailsAPI                 | OrganizationName| RepositoryName| StatusCode |
| /bitbucket/{OrganizationName}/{RepositoryName}|  ogd   | ojasonable   |   404        |