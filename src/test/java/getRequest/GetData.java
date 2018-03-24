package getRequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

import io.restassured.response.Response;

public class GetData {
	@Test
	public void testResponseCode()
	{
		given()//preconditions
		.when().get("https://myvillapluslive-api.azurewebsites.net/api/authenticate")//Actions we need to perform
		.then()//Verification part
		.statusCode(200);
		
		
//		Response resp=	get("https://myvillaplus.villaplustest.com/");
//		int code = resp.getStatusCode();
//		System.out.println("The Status Code is "+code);
//		Assert.assertEquals(code, 200);
	}
	//@Test
	public void testResponseCodeWithParams()
	{
		given()
		.param("HolidayReference", "Lastname")
		.headers("105D4BC", "Fernandes")
		.when()
		.get("https://myvillapluslive-api.azurewebsites.net/api/authenticate")
		.then()
		.statusCode(200).log().all();
		
	}
	@Test
	public void testResponseBody()
	{
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("BookingReference", "101F783");
		hash.put("CustomerLastName","Hodge");
		
		Response resp=	post("https://myvillapluslive-api.azurewebsites.net/api/authenticate",hash);
		String data = resp.asString();
		System.out.println("The data is "+data);
		Assert.assertEquals(data, 200);
		System.out.println("The Response time is "+resp.getTime());
	}
	
	//@Test
	public void testResponseDashBoard()
	{
		Response resp=	get("https://myvillaplus.villaplustest.com/");
		String data = resp.asString();
		System.out.println("The data is "+data);
		Assert.assertEquals(data, 200);
		System.out.println("The Response time is "+resp.getTime());
	}
	

}
