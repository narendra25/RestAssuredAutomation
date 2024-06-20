package com.Oneable.RestAssured.JiraApi.StepDefintion;

import java.io.IOException;

import com.Oneable.RestAssured.Utils.SpecUtils;
import com.Oneable.RestAssured.Utils.TestDataBuild;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StoryIssuesOfSpring extends SpecUtils {
	Response response;
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	TestDataBuild data = new TestDataBuild();
	String accessToken = "";

	@Before
	public void logintoken() throws IOException {
		reqspec = given().spec(requestSpecification()).body(data.addpayLoad("Navyadeepthi", "Ojas@12345"));
		resspec = RestAssured.expect().statusCode(200)
				// .contentType("application/json")
				.log().all();
		response = reqspec.when().post("/login").then().spec(resspec).log().all().extract().response();
		String body = response.asString();
		JsonPath js = new JsonPath(body);
		accessToken = js.getString("data.accessToken");
		System.out.println(accessToken);
	}

	@Given("add spring payload with {string}")
	public void add_spring_payload_with(String SprintId) throws IOException {
		reqspec = given().spec(requestSpecification()).header("x-access", accessToken)
				.body(data.SprintPayload(SprintId));
	}

	@When("user send Post request with StoryIssues of Spring api")
	public void user_send_post_request_with_story_issues_of_spring_api() {
		resspec = RestAssured.expect().statusCode(200).contentType("application/json").log().all();
		response = reqspec.when().post("/jira/sprint/story_Issues").then().spec(resspec).log().all().extract()
				.response();
	}

	@Then("Validate response body of StoryIssues of Spring deatails")
	public void validate_response_body_of_story_issues_of_spring_deatails() {
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
	}

	@Then("validate response headers of the StoryIssues of Spring  details")
	public void validate_response_headers_of_the_story_issues_of_spring_details() {

	}

}
