package com.Oneable.RestAssured.GiteaApi.StepDefintion;

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

public class Negitive extends SpecUtils{
	Response response;
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	TestDataBuild data = new TestDataBuild();
	String accessToken = "";

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
	@Given("Add Organization payload with Endpoint for API")
	public void add_organization_payload_with_endpoint_for_api() throws IOException {
		reqspec = given().spec(requestSpecification()).header("x-access", accessToken);

	}

	@When("User send Post request using get Organization Details Api {string}  {string}")
	public void user_send_post_request_using_get_organization_details_api(String GetOrgApi, String Organizationname) {
		resspec = RestAssured.expect().statusCode(200).contentType("text/plain;charset=UTF-8").log().all();
		response = reqspec.when().pathParam("OrganizationName", Organizationname).
				get(GetOrgApi).then().spec(resspec).log().all().extract().response();
	}

	@Then("Validate the Response body from Get Organization Details for Negitive")
	public void validate_the_response_body_from_get_organization_details_for_negitive() {
		String responseBody=response.getBody().asString();

		System.out.println(responseBody);	
	}



}
