package com.Oneable.RestAssured.GiteaApi.StepDefintion;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.client.methods.RequestBuilder;
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
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;

public class Getuserdetails extends SpecUtils  {
	Response response;
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	TestDataBuild data = new TestDataBuild();
	String accessToken = "";
	String file = getReportNameWithTimeStamp();
	String fullReportPath = System.getProperty("user.dir") + "\\ExtentReports\\" + file;
	private static final Logger LOG = LogManager.getLogger(Getuserdetails.class);

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
		//LOG.info("Access Token" + accessToken);
		propertiesLoad();
		extentReportSpark();

	}
	@AfterClass
	public void close() {
		extent.flush();
	}
	@Given("I set The API to The EndPoint")
	public void i_set_the_api_to_the_end_point() throws IOException {
		reqspec = given().spec(requestSpecification()).header("x-access", accessToken);
	}

	@When("I Send A GET Request to the GetUserDetail API")
	public void i_send_a_get_request_to_the_get_user_detail_api() throws IOException {

		String filePath=System.getProperty("user.dir");
		test=extent.createTest("Validate Get User Details API In GitHubEA Module").assignCategory("Functional").assignAuthor("Narendra");
		// Initialize Excel file
		FileInputStream file=new FileInputStream(filePath+properties.getProperty("DataFile"));
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheet("GetUserDetails");
		// Iterate through the rows and columns to read the data
		for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);
			String getUserDetailsAPI = row.getCell(0).getStringCellValue();
			String organizationName = row.getCell(1).getStringCellValue();
			String repositoryName= row.getCell(2).getStringCellValue();
			String userName= row.getCell(3).getStringCellValue();
			String ContentType= row.getCell(4).getStringCellValue();
			QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(reqspec);
			resspec = RestAssured.expect().statusCode(200).contentType(ContentType).log().all();
			response = reqspec.
					when()
					.pathParam("organizationName",organizationName)
					.pathParam("repositoryName",repositoryName)
					.pathParam("userName", userName)
					.get(getUserDetailsAPI)
					.then()
					.spec(resspec)
					.log().all().extract().response();
			logInfoDetails("Path Parameters Of API");
			logPassDetails("organizationName: "+organizationName);
			logPassDetails("repositoryName: "+repositoryName);
			logPassDetails("userName: "+userName);
			printRequestLogInReport(reqspec);		
			printResponseLogInReport(response);			
			LOG.info("API Base URL IS: "+queryableRequestSpecification.getBaseUri());
			LOG.info("API URL Is: "+queryableRequestSpecification.getURI());
			LOG.info("The Status Code Is  :"+response.getStatusLine());
			LOG.info("The Status Code Is  :"+response.headers().asList());
			LOG.info("The Body is Pretty print :"+response.jsonPath().prettyPrint());
		}
	}

	@Then("The Response Status Code of GetUserDetail API")
	public void the_response_status_code_of_get_user_detail_api() {
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(200, statuscode);
		LOG.info("The StatusCode"+statuscode);
	}

	@Then("the response should be contain user data of GetUserDetail API")
	public void the_response_should_be_contain_user_data_of_get_user_detail_api() {
		String responseBody=response.getBody().asString();

		JsonPath jsonPath = new JsonPath(responseBody);
		String name= jsonPath.prettyPrint();
		assertTrue(responseBody.contains("name"));
		assertTrue(responseBody.contains("email"));
		LOG.info("the name is:"+name);

	}


}
