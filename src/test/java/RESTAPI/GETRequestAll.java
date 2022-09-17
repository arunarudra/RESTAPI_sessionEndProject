package RESTAPI;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETRequestAll {
	
	public RequestSpecification request;
	public Response response;
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI="https://reqres.in/api/users";
		request=RestAssured.given();
		response = request.get();
	}
	
	@Test
	public void test1() {
		
		String resBody = response.getBody().prettyPrint();
		System.out.println(resBody);
		
	}
	
	@Test
	public void test2() {
		int resCode = response.getStatusCode();
		Assert.assertEquals(resCode, 200);
		System.out.println(resCode);
		
	}

}
