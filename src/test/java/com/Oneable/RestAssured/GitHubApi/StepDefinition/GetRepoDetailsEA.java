package com.Oneable.RestAssured.GitHubApi.StepDefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.Oneable.RestAssured.Utils.SpecUtils;
import com.Oneable.RestAssured.Utils.TestDataBuild;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GetRepoDetailsEA extends SpecUtils{
	
	Response response;
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	TestDataBuild data = new TestDataBuild();
	String accessToken = "";
	private static final Logger LOG = LogManager.getLogger(GetRepoDetailsEA.class);
	@Before
	public void logintoken() throws IOException {
		reqspec = given().spec(requestSpecification()).body(data.addpayLoad("Navyadeepthi", "Ojas@12345"));
		resspec = RestAssured.expect().statusCode(200).contentType("application/json").log().all();
		response = reqspec.when().post("/login").then().spec(resspec).log().all().extract().response();
		String body = response.asString();
		JsonPath js = new JsonPath(body);
		accessToken = js.getString("data.accessToken");
	}
	
	@Given("I set That the API to The EndPoint of GetRepoDetails API")
	public void i_set_that_the_api_to_the_end_point_of_get_repo_details_api() throws IOException {
		reqspec = given().spec(requestSpecification()).header("x-access", accessToken);

	}

	@When("I Send A GET Request to the GetRepoDetails API {string}")
	public void i_send_a_get_request_to_the_get_repo_details_api(String GetRepoAPI) {
		resspec = RestAssured.expect().statusCode(200).contentType("application/json").log().all();
		response = reqspec.when().get(GetRepoAPI).then().spec(resspec).log().all().extract().response();
		LOG.info("The Status Code Is  :"+response.getStatusLine());
		LOG.info("The Body is Pretty print :"+response.jsonPath().prettyPrint());
	}

	@Then("The Response Status Code of GetRepoDetails API {int}")
	public void the_response_status_code_of_get_repo_details_api(Integer StatusCode) {
		int statuscode = response.getStatusCode();
		LOG.info("The Status Code" + statuscode);
		//Assert.assertEquals(StatusCode, statuscode);
	}

	@Then("the response should be contain all user data of GetRepoDetails API")
	public void the_response_should_be_contain_all_user_data_of_get_repo_details_api() {
		String responseBody = response.getBody().asString();
	}

}
