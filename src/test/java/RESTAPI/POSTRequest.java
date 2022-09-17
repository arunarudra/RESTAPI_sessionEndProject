package RESTAPI;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POSTRequest {
	public RequestSpecification request;
	public Response response;
	
	public static String randomString() {
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return(generatedString);
		

	}
	
	@BeforeMethod
	public void setup() {
		String uri= "https://gorest.co.in/public/v2/users";
		String apiCall=uri;
		JSONObject jobj = new JSONObject();
		String emailstr= randomString()+"@gmail.com";
	
		jobj.put("name","testname");
		jobj.put("email",emailstr);
		jobj.put("gender","Female");
		jobj.put("status","Active");
		
		String payLoad = jobj.toString();
		String token = "dba5075ef2bfe3035c8e6b448886449bd9533cf11638170a3f1b78d785f0e547";
		String authToken = "Bearer "+token;
		request = RestAssured.given();
		response=request.header("Authorization",authToken).header("Content-Type","application/json")
				.body(payLoad)
				.post(uri);
		int respcode= response.getStatusCode();
		System.out.println(respcode);
	}
	
	
	
	@Test
	public void test2() {
		int resCode = response.getStatusCode();
		Assert.assertEquals(resCode, 201);
		System.out.println(resCode);
		
		
	}

}
