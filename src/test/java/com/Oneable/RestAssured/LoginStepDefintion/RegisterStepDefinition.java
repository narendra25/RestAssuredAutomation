package com.Oneable.RestAssured.LoginStepDefintion;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Oneable.RestAssured.GiteaApi.StepDefintion.GetBranchesDetails;
import com.Oneable.RestAssured.Utils.SpecUtils;
import com.Oneable.RestAssured.Utils.TestDataBuild;
import com.Oneable.RestAuusred.Payloads.RegisterPojo;

import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
public class RegisterStepDefinition extends SpecUtils {

	RequestSpecification reqspec;
	ResponseSpecification resspec;
	TestDataBuild data = new TestDataBuild();
	Response response;	

	private static final Logger LOG = LogManager.getLogger(RegisterStepDefinition.class);

	@Given("Add Payload with {string} {string}")
	public void add_payload_with(String username, String password) throws IOException {

		reqspec = given().spec(requestSpecification()).
				body(data.addpayLoad(username, password));

	}

	@When("User registers RegisterAPI with post http request")
	public void user_registers_register_api_with_post_http_request() throws IOException {
		resspec = RestAssured.expect().
				//                 statusCode(200).
				//                  contentType(ContentType.JSON).
				log().all();	
		response = reqspec.when().post("/register") 
				.then().spec(resspec).extract().response();                                   
		LOG.info("The status Code Is :"+response.getStatusLine());
		LOG.info("The response is:"+response.getBody().asString());                                             
	}

	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1)
	{

		int statuscode = response.getStatusCode();
		System.out.println(statuscode);

	}

	@Then("{string} in response body is \"user registred successfully with username : \"Navyadeepthi\"")
	public void in_response_body_is_user_registred_successfully_with_username_deepthi(String string)
	{  
		if((response.getStatusCode())==200) {

			assertThat(response.path("message").toString(), equalTo("User registred successfully with username :Navyadeepthi"));
			assertThat(response.path("data").toString(), equalTo("Navyadeepthi"));
		}
		else if((response.getStatusCode())==409) {

			assertThat(response.body().prettyPrint().toString(), equalTo("User cannot be registered as user exists with the given username"));

		}
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {

		//		if((response.getStatusCode())==200) {
		//			assertThat(response.path("message").toString(), equalTo("User registred successfully with username :Deepthi"));
		//			assertThat(response.path("data").toString(), equalTo("Deepthi"));
		//			}
		//		
	}


}
