package com.Oneable.RestAssured.JBitbucketApi.StepDefintion;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.Oneable.RestAssured.GiteaApi.StepDefintion.GetBranchesDetails;
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

public class GetOrganzationRepoDeatails extends SpecUtils{
	Response response;
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	TestDataBuild data = new TestDataBuild();
	String accessToken = "";
	private static final Logger LOG = LogManager.getLogger(GetOrganzationRepoDeatails.class);

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
	@Given("Add Get Organization Repo Details with Endpoint")
	public void add_get_organization_repo_details_with_endpoint() throws IOException {
		reqspec = given().spec(requestSpecification()).header("x-access", accessToken);
	}

	@When("User send GET request using Get Organization Repo Details API {string} {string} {string} {int}")
	public void user_send_get_request_using_get_organization_repo_details_api(String GetOrgApi, String org, String repo, int StatusCode) {
		resspec = RestAssured.expect().statusCode(StatusCode).contentType("application/json").log().all();
		response = reqspec.when().pathParam("OrganizationName",org).pathParam("RepositoryName",repo).get(GetOrgApi).then().spec(resspec).log().all().extract().response();
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(StatusCode, statuscode);
		LOG.info("The status Code Is :"+response.getStatusLine());
		LOG.info("The response is:"+response.getBody().asPrettyString());

}

	@Then("Validate the Response body from Get Organization Repo Details Scenario")
	public void validate_the_response_body_from_get_organization_repo_details_scenario() {
		String responseBody=response.getBody().asString();
		LOG.info("The response body is:"+responseBody);	
	}

	
}
