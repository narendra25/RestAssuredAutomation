package com.Oneable.RestAssured.JiraApi.StepDefintion;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

public class SprintDeatils extends SpecUtils {
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	TestDataBuild data = new TestDataBuild();
	Response response;
	 String accessToken = "";
	
	 
@Before
public void logintoken() throws IOException {
	reqspec = given().spec(requestSpecification()).
			body(data.addpayLoad("Navyadeepthi", "Ojas@12345"));
	resspec = RestAssured.expect().
            statusCode(200).
            contentType("application/json").
             log().all();
	response = reqspec.when().post("/login") 
	         .then().spec(resspec)
	         .log().all()
               .extract()                 
               .response();
	String body =response.asString();
    JsonPath js =new JsonPath(body);
   accessToken = js.getString("data.accessToken");
   System.out.println(accessToken);
}
        @Given("add sprint payload with {string}")
    public void add_sprint_payload_with(String sprintId) throws IOException {
	    reqspec = given().spec(requestSpecification()).header("x-access",accessToken).
			body(data.SprintPayload(sprintId));
    
    }
	@When("user send Post request with sprintdetails api")
	public void user_send_post_request_with_sprintdetails_api() {
		resspec = RestAssured.expect().
	              statusCode(200).
	              contentType("application/json").
	               log().all();	
       response = reqspec.when().post("/jira/sprint/details") 
	                            .then().spec(resspec)
	                            .log().all()
	                            .extract()
	                            .response();
	}

	@Then("Validate response body of sprint deatails")
	public void validate_response_body_of_sprint_deatails() {
		if((response.getStatusCode())==200) {
			
			assertThat(response.path("name").toString(), equalTo("SPT Sprint 1"));
			assertThat(response.path("state").toString(), equalTo("active"));
			assertThat(response.path("originBoardId").toString(), equalTo("2"));
		}
	}

	@Then("validate response headers of the sprint details")
	public void validate_response_headers_of_the_sprint_details() {
		
	//	assertThat(response.header("contentType"), equalTo("application/json") );
	    
	}




}
