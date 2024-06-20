package com.Oneable.RestAssured.TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
		"src/test/resources/AuthenticationFeatures/Login.feature",
		"src/test/resources/AuthenticationFeatures/Register.feature",
		"src/test/resources/JiraFeatures/LisiOfIssuesAssineeStatus.feature",
		"src/test/resources/JiraFeatures/ListOfIssuesForParticularAssignee.feature",
		"src/test/resources/JiraFeatures/SprintDetails.feature",
		"src/test/resources/JiraFeatures/StoryIssues.feature",
		"src/test/resources/GitHubEAModule/GetOrgDetails.feature",
		"src/test/resources/GitHubEAModule/Getuserdetails.feature",
},
glue = {
		"com/Oneable/RestAssured/LoginStepDefintion",
		"com/Oneable/RestAssured/JiraApi/StepDefintion",
		"com/Oneable/RestAssured/GiteaApi/StepDefintion",
		
},
plugin = "json:target/jsonReports/cucumber-report.json",
dryRun = false,

monochrome = true)

public class RegressionRunner {

}
