@BitBucketGetRepoDetails
Feature: Verify the BitBucket Get Repo Details

  Scenario Outline: Validate BitBucket Get Repo Details           
    Given Add  BitBucket Get Repo Details with Endpoint
    When User send BitBucket GET request using Get Repo Details API "<GetOrganizationRepoDetailsAPI>" "<RepositoryName>" <StatusCode>
    Then Validate the Response body from BitBucket Get Repo Details Scenario
    
     Examples:
| GetOrganizationRepoDetailsAPI |RepositoryName| StatusCode |
| /bitbucket/{RepositoryName}   | ojasonable   |   200        |