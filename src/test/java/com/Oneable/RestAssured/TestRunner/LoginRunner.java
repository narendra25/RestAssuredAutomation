package com.Oneable.RestAssured.TestRunner;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/OneableFeatures",
                glue = {"com/Oneable/RestAssured/LoginStepDefintion"},
                tags = "@login",
                dryRun =false,
       
                monochrome =true
		
                 )
public class LoginRunner {
	
	
	
	

}
