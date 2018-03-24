package com.vp.tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.vp.restutils.GetFromDB;
import com.vp.restutils.TestBase;
import com.vp.wsmethods.RestClient;

import io.restassured.response.Response;

public class AutheniticateAPITest extends TestBase{
	
	String URI;
	RestClient restClient;
	TestBase testBase;
	Response resp;
	GetFromDB getData;
	String Auth_JSONString;
	List<String> TestData; 
	
	@BeforeMethod
	public void Setup() throws ClassNotFoundException, SQLException, IOException
	{
		testBase = new TestBase();
		URI = prop.getProperty("AuthURL");
		System.out.println(URI);
		
		String DataFrom = "DB";
		
		if(DataFrom == "File")
		{
			TestData = testBase.TestData();
		}
		else if(DataFrom=="DB")
		{
			getData = new GetFromDB();
			TestData = getData.getCustData();
		}
		Random ran = new Random();
		int index = ran.nextInt(TestData.size());
		String SelData = TestData.get(index);
		String[] celldata = SelData.split("\t");
				
		Auth_JSONString = "{\"BookingReference\" : \""+celldata[3]+"\",\"CustomerLastName\" : \""+celldata[1]+"\"}";
		System.out.println(Auth_JSONString);
	}	

	@Test()
	public void Auth_TC_1_WithValidCredentials() 
	{		
//		String JSONString = "{\r\n" + 
//				"	\"BookingReference\" : \"101F783\",\r\n" + 
//				"	\"CustomerLastName\" : \"Hodge\"\r\n" + 
//				"}";
		resp = RestClient.post(URI, Auth_JSONString);
		
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
		String body = resp.asString();
		System.out.println(body);
		Assert.assertTrue(StatusCode==RESPONSE_STATUS_CODE_200);
		Reporter.log("PASS : Authenticate API status code is "+StatusCode);
	}
	
	@Test
	public void Auth_TC_2_WithInValidCredentials()
	{
//		String JSONString = "{\r\n" + 
//				"	\"BookingReference\" : \"101F783\",\r\n" + 
//				"	\"CustomerLastName\" : \"test\"\r\n" + 
//				"}";
		resp = RestClient.post(URI, Auth_JSONString);
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
		String body = resp.asString();
		System.out.println(body);
		Assert.assertTrue(StatusCode==RESPONSE_STATUS_CODE_401);
		Reporter.log("PASS : Authenticate API status code is "+StatusCode);
	}
}
