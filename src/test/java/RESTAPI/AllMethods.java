package RESTAPI;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AllMethods {
	
	public RequestSpecification request;
	public Response response;
	String baseuri = "https://reqres.in/api/users";
	
	//To generate random string for email field
	
	public static String randomString() {
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return(generatedString);
		

	}
	
	//GET Method to list all users
	public Response GetAllUsers() {
		
		RestAssured.baseURI=baseuri;
		request=RestAssured.given();
		response = request.get();
		return response;
	}
	
	//POST method to create a new user
	public Response CreateUser() {
		String uri= "https://gorest.co.in/public/v2/users";
		JSONObject jobj= new JSONObject();
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
		
		return response;
		
	}
	
	//PUT Method to update the details of a specific user
	
	public Response PutMethod(int uid, String usrname, String gend) {
		String uri= "https://gorest.co.in/public/v2/users";
		JSONObject jobj= new JSONObject();
		jobj.put("name", usrname);
		jobj.put("gender", gend);
		String token = "dba5075ef2bfe3035c8e6b448886449bd9533cf11638170a3f1b78d785f0e547";
		String authToken = "Bearer "+token;
		request=RestAssured.given();
		response= request.header("Authorization",authToken).header("Content-Type","application/json").body(jobj.toString()).put("/"+uid);
		
		return response;
		
	}
	
	//DELETE Method to delete the added user
	public Response deleteMethod(int uid) {
		String uri= "https://gorest.co.in/public/v2/users";
		String token = "dba5075ef2bfe3035c8e6b448886449bd9533cf11638170a3f1b78d785f0e547";
		String authToken = "Bearer "+token;
		request=RestAssured.given();
		response = request.header("Authorization",authToken).header("Content-Type","application/json").delete("/"+uid);
		return response;
	}
	
	//GET Method to check whether the user is present or not
	public Response getSingleEmpMethod(int uid) {
		String uri= "https://gorest.co.in/public/v2/users";
		request=RestAssured.given();
		response = request.get("/"+uid);
		return response;
	}

}
