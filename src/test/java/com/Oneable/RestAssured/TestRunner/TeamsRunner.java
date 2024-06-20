package com.Oneable.RestAssured.TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/TeamsFeatures/Meeting Time.feature","src/test/resources/TeamsFeatures/MeetingCount.feature","src/test/resources/TeamsFeatures/ChatMessages.feature"},
           glue = {"com/Oneable/RestAssured/Teams/StepDefintion" },
           plugin = "json:target/jsonReports/cucumber-report.json",
		    dryRun = false,

		    monochrome = true)

public class TeamsRunner {

}
