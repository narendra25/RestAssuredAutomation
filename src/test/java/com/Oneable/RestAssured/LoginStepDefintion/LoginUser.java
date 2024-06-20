package com.Oneable.RestAssured.LoginStepDefintion;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.Oneable.RestAssured.GiteaApi.StepDefintion.GetBranchesDetails;
import com.Oneable.RestAssured.Utils.SpecUtils;
import com.Oneable.RestAssured.Utils.TestDataBuild;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class LoginUser extends SpecUtils{
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	TestDataBuild data = new TestDataBuild();
	Response response;
	 String accessToken = "";
	 private static final Logger LOG = LogManager.getLogger(LoginUser.class);
	@Given("user payload login with {string} {string}")
	public void user_payload_login_with(String username, String password) throws IOException {
		reqspec = given().spec(requestSpecification()).
				body(data.addpayLoad(username, password));
	}

	@When("user logins loginApi with post http request")
	public void user_logins_login_api_with_post_http_request() {
		resspec = RestAssured.expect().
				              statusCode(200).
				              contentType("application/json").
				               log().all();	
	   response = reqspec.when().post("/login") 
				         .then().spec(resspec)
				         .log().all()
				         .assertThat()
				            .body("message", equalTo("Token token generated successfully for user : Navyadeepthi"))
			                .body("data.username", equalTo("Navyadeepthi"))
			                .body("data.accessToken", notNullValue())
			                .extract()                 
			                .response();
	   LOG.info("The statuscode is "+response.getStatusLine());
	   LOG.info("The Response  is "+response.jsonPath().prettyPrint());
	   
	    
	}

	@Then("{string} in the response body is {string}")
	public void in_the_response_body_is(String string, String string2) {
		
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		LOG.info("The statuscode is "+statuscode);
			
		
	}

	@Then("accesstoken validation in the response body")
	public void accesstoken_validation_in_the_response_body() {
		
		       String body =response.asString();
		       JsonPath js =new JsonPath(body);
		      accessToken = js.getString("data.accessToken");
		      System.out.println(accessToken);
		      
	}


}
