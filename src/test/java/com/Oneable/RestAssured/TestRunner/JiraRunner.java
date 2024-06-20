package com.Oneable.RestAssured.TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/JiraFeatures",
           glue = {"com/Oneable/RestAssured/JiraApi/StepDefintion" },
           plugin = "json:target/jsonReports/cucumber-report.json",
	            tags = "@sprintdetails",
		       //tags = "@StoryIssuesofSpring",
		       //tags = "@ListofIssuesforAssignee",
		       //  tags=" @ListOfIssuesAssineeStatus",
		    dryRun = false,

		    monochrome = true)

public class JiraRunner {

}