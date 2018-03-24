package com.vp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vp.restutils.TestBase;
import com.vp.wsmethods.RestClient;

import io.restassured.response.Response;

public class DashBoardAPITest extends TestBase{
	
	String URI;
	RestClient restClient;
	TestBase testBase;
	Response resp;
	HashMap<String, String> headerMap;
	
	@BeforeMethod
	public void Setup()
	{
		testBase = new TestBase();
		URI = prop.getProperty("DashBoardURL");
		System.out.println(URI);
	}
	
	@Test
	public void DashBoard_TC_1_WithValidCredentials()
	{
		String JSONString = "{"+
				"\"lName\" : \"Hodge\""+
		"}";
//		headerMap.put("lname", "Hodge");
		resp = RestClient.get(URI, JSONString);
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
		String body = resp.asString();
		System.out.println(body);

			Assert.assertTrue(StatusCode==RESPONSE_STATUS_CODE_200);
			Reporter.log("PASS : Dash Board API status code is "+StatusCode);

	}
	
	@Test
	public void DashBoard_TC_2_WithInValidCredentials()
	{
		String JSONString = "{"+
				"\"lName\" : \"Veera\""+
		"}";
//		headerMap.put("lname", "Veera");
		resp = RestClient.get(URI, JSONString);
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
		String body = resp.asString();
		System.out.println(body);

			Assert.assertTrue(StatusCode==RESPONSE_STATUS_CODE_401);
			Reporter.log("PASS : Dash Board API status code is "+StatusCode);
		
	}

}
