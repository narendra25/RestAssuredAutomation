package com.Oneable.RestAssured.GiteaApi.StepDefintion;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

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

public class GetOrgDetails extends SpecUtils {
	
	Response response;
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	TestDataBuild data = new TestDataBuild();
	String accessToken = "";
	private static final Logger LOG = LogManager.getLogger(GetOrgDetails.class);
	@Before
	public void logintoken() throws IOException {
		reqspec = given().spec(requestSpecification()).body(data.addpayLoad("Navyadeepthi", "Ojas@12345"));
		resspec = RestAssured.expect().statusCode(200)   
				.log().all();
		response = reqspec.when().post("/login").then().spec(resspec).log().all().extract().response();
		String body = response.asString();
		JsonPath js = new JsonPath(body);
		accessToken = js.getString("data.accessToken");
		System.out.println(accessToken);
		System.out.println(accessToken);
	}
	
	@Given("Add Organization Repo payload with Endpoint")
	public void add_organization_payload_with_and() throws IOException {
		reqspec = given().spec(requestSpecification()).header("x-access", accessToken);
	}


@When("User send Post request using getOrganizationRepoDetails Api {string}  {int}")
public void user_send_post_request_using_get_organization_details_api(String GetOrgApi , int StatusCode) {
		resspec = RestAssured.expect().statusCode(StatusCode).contentType("text/plain;charset=UTF-8").log().all();
		response = reqspec.when().get(GetOrgApi).then().spec(resspec).log().all().extract().response();
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(StatusCode, statuscode);
		LOG.info("The Status Code Is  :"+response.getStatusLine());
		LOG.info("The Body is Pretty print :"+response.getBody().asPrettyString());
	}

	@Then("Validate  Response body from Get Organization Repo Details")
	public void validate_the_response_body_from_get_organization_details() {
		String responseBody=response.getBody().asString();

		System.out.println(responseBody);	
		assertTrue(responseBody.contains("login_name"));
		assertTrue(responseBody.contains("owner"));
		assertTrue(responseBody.contains("name"));
	}

	
	

}
