package com.vp.wsmethods;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	static RequestSpecification reqSpec; 
	static Response resp;
	
	public static Response post(String URI, String JSONString)
	{
		reqSpec = RestAssured.given().body(JSONString);
		reqSpec.contentType(ContentType.JSON);
		reqSpec.accept(ContentType.JSON);
		resp = reqSpec.post(URI);
		return resp;
		
	}
	//get method without headers
	public static Response get(String URI)
	{
		reqSpec = RestAssured.given();
		reqSpec.contentType(ContentType.JSON);
		reqSpec.accept(ContentType.JSON);
		Response resp = reqSpec.get(URI);	
		return resp;
		
	}
	//get method with headers
//		public static Response get(String URI, HashMap<String, String> headerMap)
		public static Response get(String URI, String JSONString)
		{
			reqSpec = RestAssured.given();
			reqSpec.contentType(ContentType.JSON);
			reqSpec.accept(ContentType.JSON);
			reqSpec.header("lName","Hodge");
			Response resp = reqSpec.get(URI);	
			return resp;
			
		}
	
	public static Response put(String URI, String JSONString)
	{
		reqSpec = RestAssured.given().body(JSONString);
		reqSpec.contentType(ContentType.JSON);
		Response resp = reqSpec.put(URI);
		return resp;
		
	}
	
	public static Response delete(String URI)
	{
		reqSpec = RestAssured.given();
		reqSpec.contentType(ContentType.JSON);
		Response resp = reqSpec.delete(URI);
		return resp;
		
	}

}
