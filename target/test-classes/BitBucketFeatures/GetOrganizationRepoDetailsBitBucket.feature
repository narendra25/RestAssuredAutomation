@GetOrganizationRepoDeatilsBitBucket
Feature: Verify the Get Organization Repo Details

  Scenario Outline: Validate Get Organization Repo Details           
    Given Add Get Organization Repo Details with Endpoint
    When User send GET request using Get Organization Repo Details API "<GetOrganizationRepoDetailsAPI>" "<OrganizationName>" "<RepositoryName>" <StatusCode>
    Then Validate the Response body from Get Organization Repo Details Scenario
    
     Examples:
| GetOrganizationRepoDetailsAPI                 | OrganizationName| RepositoryName| StatusCode |
| /bitbucket/{OrganizationName}/{RepositoryName}|    organization | ojasonable   |   200        |