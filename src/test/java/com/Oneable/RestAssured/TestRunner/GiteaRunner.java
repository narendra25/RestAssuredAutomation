package com.Oneable.RestAssured.TestRunner;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features = {
		"src/test/resources/GitHubEAModule/GetBranchDetails.feature",
		"src/test/resources/GitHubEAModule/GetbranchesGetails.feature",
		"src/test/resources/GitHubEAModule/Getuserdetails.feature",
		"src/test/resources/GitHubEAModule/GetOrgDetails.feature",
		"src/test/resources/GitHubEAModule/GetRepoDetails.feature",
},
glue = {
		"com/Oneable/RestAssured/GiteaApi/StepDefintion",
},
plugin = {"json:target/jsonReports/cucumber-report.json"},

dryRun = false,

monochrome = true)

public class GiteaRunner {

}
