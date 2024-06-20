package com.Oneable.RestAssured.JiraApi.StepDefintion;

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

public class IssuesAssineeStatus extends SpecUtils {

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
	@Given("add the assigne payload with {string} {string}")
	public void add_the_assignee_payload_with(String assigneeAccountId, String userStatus) throws IOException {
		reqspec = given().spec(requestSpecification()).header("x-access", accessToken)
				.body(data.statuspayload(assigneeAccountId ,userStatus));
	}

	@When("user send Post request with ListOfIssuesAssineeStatus api")
	public void user_send_post_request_with_list_of_issues_assinee_status_api() {
	   
		resspec = RestAssured.expect().statusCode(200).contentType("application/json").log().all();
		response = reqspec.when().post("/jira/issues").then().spec(resspec).log().all().extract().response();
	}

	@Then("Validate response body of ListOfIssuesAssineeStatus")
	public void validate_response_body_of_list_of_issues_assinee_status() {
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		}
}