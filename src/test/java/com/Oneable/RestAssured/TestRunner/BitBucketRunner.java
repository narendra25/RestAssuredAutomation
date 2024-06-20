package com.Oneable.RestAssured.TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "/RestAssuredwithBDD/src/test/resources/BitBucketFeatures",
                glue = {"com/Oneable/RestAssured/JBitbucketApi/StepDefintion"},
                plugin= "json:target/jsonReports/cucumber-report.json",
                tags = "@GetUserDetails",
               // tags=  "@GetBranchesDetails",
                dryRun =false,
                monochrome =true
)
public class BitBucketRunner 
{

}