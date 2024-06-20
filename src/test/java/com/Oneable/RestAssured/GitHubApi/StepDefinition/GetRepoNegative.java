package com.Oneable.RestAssured.GitHubApi.StepDefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;

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

public class GetRepoNegative extends SpecUtils {
	
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
	}
	
	@Given("Add Get Repo GIT payload with Endpoint for API")
	public void add_get_repo_git_payload_with_endpoint_for_api() throws IOException {
		reqspec = given().spec(requestSpecification()).header("x-access", accessToken);
	}

	@When("User send Post request using get Repo Details GIT Api {string}  {int}")
	public void user_send_post_request_using_get_repo_details_git_api(String GetOrgApi, int StatusCode) {
		resspec = RestAssured.expect().statusCode(200).contentType("application/json").log().all();
		response = reqspec.when().
				get(GetOrgApi).then().spec(resspec).log().all().extract().response();
		LOG.info("The Status Code Is  :"+response.getStatusLine());
		LOG.info("The Body is Pretty print :"+response.jsonPath().prettyPrint());
	
	}

	@Then("Validate the Response body from Get Repo Details GIT for Negitive")
	public void validate_the_response_body_from_get_repo_details_git_for_negitive() {
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);	
	}

}
