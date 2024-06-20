package com.Oneable.RestAssured.TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features =
"src/test/resources/GitHubModule/GetRepoNegative.feature",
           glue = {"com/Oneable/RestAssured/GitHubApi/StepDefinition" },
           plugin = "json:target/jsonReports/cucumber-report.json",	           
		      
		    dryRun = false,

		    monochrome = true)
public class GitHubRunner {

}
