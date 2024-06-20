package com.Oneable.RestAssured.GitHubApi.StepDefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;

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

public class GetOrgRepoDetailsNegitive extends SpecUtils {
	Response response;
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	TestDataBuild data = new TestDataBuild();
	String accessToken = "";

	@Before
	public void logintoken() throws IOException {
		reqspec = given().spec(requestSpecification()).body(data.addpayLoad("Navyadeepthi", "Ojas@12345"));
		resspec = RestAssured.expect().statusCode(200).contentType("application/json").log().all();
		response = reqspec.when().post("/login").then().spec(resspec).log().all().extract().response();
		String body = response.asString();
		JsonPath js = new JsonPath(body);
		accessToken = js.getString("data.accessToken");
		// LOG.info("Access Token" + accessToken);
	}
	@Given("I set That the API to The EndPoint of orgRepoAPI for negitive")
	public void i_set_that_the_api_to_the_end_point_of_org_repo_api_for_negitive() throws IOException {
		reqspec = given().spec(requestSpecification()).header("x-access", accessToken);
	}

	@When("I Send A GET Request to the orgRepoAPI for negitive {string}")
	public void i_send_a_get_request_to_the_org_repo_api_for_negitive(String GetOrgRepoAPI) {
		resspec = RestAssured.expect().statusCode(404).contentType("application/json").log().all();
		response = reqspec.when().get(GetOrgRepoAPI).then().spec(resspec).log().all().extract().response();
		LOG.info("The Status Code Is  :"+response.getStatusLine());
		LOG.info("The Body is Pretty print :"+response.jsonPath().prettyPrint());
	}

	@Then("The Response Status Code of orgRepoAPI for negitive {int}")
	public void the_response_status_code_of_org_repo_api_for_negitive(int StatusCode) {
		int statuscode = response.getStatusCode();
		LOG.info("The Status Code" + statuscode);
		Assert.assertEquals(StatusCode, statuscode);
		
	}

	@Then("the response should be contain all user data of orgRepoAPI for negitive")
	public void the_response_should_be_contain_all_user_data_of_org_repo_api_for_negitive() {
		String responseBody = response.getBody().asString();
		LOG.info("The ResponseBody Is:" + responseBody);
	}



}
