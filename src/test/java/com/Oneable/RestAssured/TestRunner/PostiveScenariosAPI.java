package com.Oneable.RestAssured.TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
		"src/test/resources/AuthenticationFeatures/Login.feature",
		
		"src/test/resources/AuthenticationFeatures/Register.feature",

		"src/test/resources/GitHubEAModule/GetBranchDetails.feature",
		"src/test/resources/GitHubEAModule/GetbranchesGetails.feature",
		"src/test/resources/GitHubEAModule/GetOrgDetails.feature",
		"src/test/resources/GitHubEAModule/GetRepoDetails.feature",
		"src/test/resources/GitHubEAModule/Getuserdetails.feature",
		
		"src/test/resources/GitHubModule/GetOrganizationRepoDetails.feature",
		"src/test/resources/GitHubModule/GetRepoDetailsGit.feature",
		
		"src/test/resources/BitBucketFeatures/BitbucketGetBranchesDetails.feature",
		"src/test/resources/BitBucketFeatures/BitbucketuserDetails.feature",
		"src/test/resources/BitBucketFeatures/GetOrganizationRepoDetailsBitBucket.feature",
		"src/test/resources/BitBucketFeatures/BitBucketGetRepoDetails.feature",
		
		"src/test/resources/JiraFeatures/LisiOfIssuesAssineeStatus.feature",
		"src/test/resources/JiraFeatures/ListOfIssuesForParticularAssignee.feature",
		"src/test/resources/JiraFeatures/SprintDetails.feature",
		"src/test/resources/JiraFeatures/StoryIssues.feature",
		
		"src/test/resources/TeamsFeatures/ChatMessages.feature",
		"src/test/resources/TeamsFeatures/MeetingCount.feature",
		"src/test/resources/TeamsFeatures/Meeting Time.feature",
		
		
},
glue = {
		"com/Oneable/RestAssured/LoginStepDefintion",
		"com/Oneable/RestAssured/GiteaApi/StepDefintion",
		"com/Oneable/RestAssured/GitHubApi/StepDefintion",
		"com/Oneable/RestAssured/JBitbucketApi/StepDefintion",
		"com/Oneable/RestAssured/JiraApi/StepDefintion",
		"com/Oneable/RestAssured/Teams/StepDefintion",
},
plugin = "json:target/jsonReports/cucumber-report.json",
dryRun = false,

monochrome = true)


public class PostiveScenariosAPI {

}
