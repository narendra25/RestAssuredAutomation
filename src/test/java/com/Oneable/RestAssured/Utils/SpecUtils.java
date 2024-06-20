package com.Oneable.RestAssured.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mongodb.util.JSON;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSenderOptions;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
public class SpecUtils {
	public static Properties properties;
	public static Date date = new Date();
	public static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
	public static String dt = formatter.format(date);
	protected static final Logger LOG = (Logger) LogManager.getLogger(SpecUtils.class);
	public static FileReader fileReader;
	public static ExtentHtmlReporter spark;
	public static ExtentTest test;
	public static ExtentReports extent;
	static String fileName =getReportNameWithTimeStamp();
	public static String fullReportPath = System.getProperty("user.dir") + "\\ExtentReports\\" + fileName;
    public static File file=new File(fullReportPath);
    
	RequestSpecification requestSpecification;
	RequestSpecBuilder requestSpecBuilder;
	
	public RequestSpecification requestSpecification() throws IOException 
	{
		PrintStream log = new PrintStream(new File("Logs/logging_"+dt+".txt"));
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
				setBaseUri(getGlobalValue("baseUrl")).
				addFilter(RequestLoggingFilter.logRequestTo(log)).
				addFilter(ResponseLoggingFilter.logResponseTo(log)).
				setContentType(ContentType.JSON).
				log(LogDetail.ALL);
		requestSpecification = requestSpecBuilder.build();
		return requestSpecification;

	}
	public static String getGlobalValue(String key) throws IOException {
		Properties prop =new Properties();
		FileInputStream fis =new FileInputStream("./src/test/java/com/Oneable/RestAssured/Utils/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}

	//Loading Properties File 
	public void propertiesLoad() throws IOException {

		try {
			properties = new Properties();
			String filePath=System.getProperty("user.dir");
			System.out.println(filePath);

			FileInputStream file=new FileInputStream(filePath+"/src/test/java/com/Oneable/RestAssured/Utils/global.properties");
			properties.load(file);
		} catch (FileNotFoundException ex) {
			System.out.println("*************************************************");
			System.out.println("Property file you are looking for does not exist.");
			System.out.println("*************************************************");

		} catch (IOException e) {
			System.out.println("io exception");
		}
	}
	public void extentReportSpark() {
			spark= new ExtentHtmlReporter(file);
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Browser Name", properties.getProperty("BrowserName"));
			extent.setSystemInfo("Environment", properties.getProperty("Environment"));
			extent.setSystemInfo("Base URL", properties.getProperty("baseUrl"));
			extent.setSystemInfo("User Name", properties.getProperty("UserName"));
			extent.setSystemInfo("User Email", properties.getProperty("UserEmail"));
			spark.config().setDocumentTitle("Sample Automation Testing Report");
			spark.config().setReportName("Sample Automation Test Suite");
			spark.config().setTheme(Theme.DARK);
			spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
			String css = ".r-img {width: 75%;}";
		    spark.config().setCSS(css);
		}
	 public static String getReportNameWithTimeStamp() {
	        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
	        LocalDateTime localDateTime = LocalDateTime.now();
	        String formattedTime = dateTimeFormatter.format(localDateTime);
	        String reportName = "TestReport_" + formattedTime + ".html";
	        return reportName;
	    }

	protected static void printRequestLogInReport(RequestSpecification requestSpecification) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
		logPassDetails("Base URL is :" + queryableRequestSpecification.getBaseUri());
		logPassDetails("ContentType Is : " + queryableRequestSpecification.getContentType());
		logPassDetails("API URL IS : " + queryableRequestSpecification.getURI());
		logPassDetails("Method is : " + queryableRequestSpecification.getMethod());
		logInfoDetails("Headers are ");
		logPassDetails("Headers are: "+queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is ");
		logPassDetails("The Body is:  "+queryableRequestSpecification.getBody());
	}
	protected static void printResponseLogInReport(Response response) {
		logPassDetails("Response status is : " + response.getStatusCode());
		logInfoDetails("Response Headers are ");
		logHeaders(response.getHeaders().asList());
		logInfoDetails("Response body is ");
		logJson(response.jsonPath().prettyPrint());
	}
	public static void logPassDetails(String log) {
        test.pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }
    public static void logFailureDetails(String log) {
    	test.fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }
    public static void logExceptionDetails(String log) {
    	test.fail(log);
    }
    public static void logInfoDetails(String log) {
    	test.info(MarkupHelper.createLabel(log, ExtentColor.GREY));
    }
    public static void logWarningDetails(String log) {
    	test.warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
    }
    public static void logJson(String json) {
    	test.info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
    }
public static void logHeaders(List<Header> headersList) {
 
        String[][] arrayHeaders = headersList.stream().map(header -> new String[] {header.getName(), header.getValue()})
                        .toArray(String[][] :: new);
        test.info(MarkupHelper.createTable(arrayHeaders));
    }
}

