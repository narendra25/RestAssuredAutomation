package com.Oneable.RestAssured.JBitbucketApi.StepDefintion;
import com.Oneable.RestAssured.Utils.SpecUtils;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import static org.junit.Assert.assertEquals;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
public class GetBrannchesDetailsStepDefinition extends SpecUtils
{
    Response response;
    RequestSpecification reqspec;
    ResponseSpecification resspec;
    TestDataBuild data = new TestDataBuild();
    String accessToken = "";
    private static final Logger LOG = LogManager.getLogger(GetBrannchesDetailsStepDefinition.class);
    @Before
    public void logintoken() throws IOException {
       reqspec = given().spec(requestSpecification())
             .body(data.addpayLoad("Sreelakshmii", "Sree@123"));
       resspec = RestAssured.expect()
             .statusCode(200)
          
             .log().all();
       response = reqspec.when().post("/login")
             .then()
             .spec(resspec)
             .log().all()
             .extract().response();
       String body = response.asString();
       JsonPath js = new JsonPath(body);
       accessToken = js.getString("data.accessToken");
       System.out.println(accessToken);
    }
//        @Given("User enters into GetBranches Details  with get request")
//    public void user_enters_into_get_branches_details_with_get_request() throws IOException 
//      {
//          reqspec=given().spec(requestSpecification())
//                  .header("x-access",accessToken);
//          resspec = RestAssured.expect().
//                   statusCode(200).
//                   contentType("application/json").
//                    log().all();  
//       response = reqspec.when().get("bitbucket/ojasonable/repos/gitintegration/user/Saiprasanna")
//                                 .then().spec(resspec)
//                                 .log().all()
//                                 .extract()
//                                 .response();
//       }
//
//       @Then("The API call got success with status code is {int}")
//       public void the_api_call_got_success_with_status_code_is(Integer int1) {
//          int statuscode=response.getStatusCode();
//          System.out.println(statuscode);
//          //assertEquals(response.getStatusCode(),200); 
//       
//       }
}