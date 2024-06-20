package com.Oneable.RestAssured.GiteaApi.StepDefintion;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.junit.Assert;
import org.testng.annotations.AfterSuite;

import com.Oneable.RestAssured.Utils.SpecUtils;
import com.Oneable.RestAssured.Utils.TestDataBuild;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;

public class GetBranchDetail extends SpecUtils {
	Response response;
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	TestDataBuild data = new TestDataBuild();
	String accessToken = "";
	String file = getReportNameWithTimeStamp();
	String fullReportPath = System.getProperty("user.dir") + "\\ExtentReports\\" + file;
	private static final Logger LOG = LogManager.getLogger(GetBranchDetail.class);
	@Before
	public void logintoken() throws IOException {

		reqspec = given().spec(requestSpecification()).body(data.addpayLoad("Navyadeepthi", "Ojas@12345"));
		resspec = RestAssured.expect().statusCode(200)
				.contentType("application/json")   
				.log().all();
		response = reqspec.when().post("/login").then().spec(resspec).log().all().extract().response();
		String body = response.asString();
		JsonPath js = new JsonPath(body);
		accessToken = js.getString("data.accessToken");
		propertiesLoad();
		extentReportSpark();

	}
	@After
	public void close() {
		extent.flush();
	}
	
	@Given("I set The API to The EndPoint of GetBranchDetails")
	public void i_set_the_api_to_the_end_point_of_get_branch_details() throws IOException {
		reqspec = given().spec(requestSpecification()).header("x-access", accessToken);
	}

	@When("I Send A GET Request to the GetBranchDetails API")
	public void i_send_a_get_request_to_the_get_branch_details_api() throws IOException {
	
		//Generating Reports code
		String filePath=System.getProperty("user.dir");
		test=extent.createTest("Validate Get Branch Details API In GitHubEA Module")
				.assignCategory("Functional")
				.assignAuthor("Narendra");
		
		// Initialize Excel file
		FileInputStream file=new FileInputStream(filePath+properties.getProperty("DataFile"));
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheet("GetBranchDetails");
		
		// Iterate through the rows and columns to read the data
		for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);
			String getBranchDetailsAPI = row.getCell(0).getStringCellValue();
			String organizationName = row.getCell(1).getStringCellValue();
			String repositoryName= row.getCell(2).getStringCellValue();
			String branchName= row.getCell(3).getStringCellValue();
			String ContentType= row.getCell(4).getStringCellValue();
			QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(reqspec);
			resspec = RestAssured.expect().statusCode(200).contentType(ContentType).log().all();
			response = reqspec.
					when()
					.pathParam("organizationName",organizationName)
					.pathParam("repositoryName",repositoryName)
					.pathParam("branchName",branchName)
					.get(getBranchDetailsAPI)
					.then()
					.spec(resspec)
					.log().all().extract().response();
			
			logInfoDetails("Path Parameters Of API");
			logPassDetails("organizationName: "+organizationName);
			logPassDetails("repositoryName: "+repositoryName);
			logPassDetails("branchName: "+branchName);
			printRequestLogInReport(reqspec);
			logPassDetails("Response status is : " + response.getStatusCode());
			logInfoDetails("Response Headers are ");
			logHeaders(response.getHeaders().asList());
			logInfoDetails("Response body is ");
			logPassDetails(response.getBody().asString());
			LOG.info("API Base URL IS: "+queryableRequestSpecification.getBaseUri());
			LOG.info("API URL Is: "+queryableRequestSpecification.getURI());
			LOG.info("API Headers Are : "+response.headers().asList());
			LOG.info("API Response Is : "+response.getBody().asPrettyString());
			
						
		}
	}
	@Then("The Response Status Code of GetBranchDetails API")
	public void the_response_status_code_of_get_branch_details_api() {
		int statuscode = response.getStatusCode();
		Assert.assertEquals(200, statuscode);
		LOG.info("The Status Code"+statuscode);
	}
	@Then("the response should be contain user data of GetBranchDetails API")
	public void the_response_should_be_contain_user_data_of_get_branch_details_api() {
		String responseBody=response.getBody().asString();
		//JsonPath json = new JsonPath(responseBody);
		//String name= json.prettyPrint();
		assertTrue(responseBody.contains("name"));
		assertTrue(responseBody.contains("email"));
		logPassDetails(response.getBody().asString());
	}
}
