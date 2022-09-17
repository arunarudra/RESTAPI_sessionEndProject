package RESTAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class EndToEndTest {
	
	AllMethods alm= new AllMethods();
	String resBody1;
	
	@Test
	public void test1() {
		Response response;
		
		response=alm.GetAllUsers();
		int resCode = response.getStatusCode();
		String respBody = response.getBody().prettyPrint();
		Assert.assertEquals(resCode, 200);
		System.out.println(respBody);
		
		response = alm.CreateUser();
		int resCode1 = response.getStatusCode();
		Assert.assertEquals(response.getStatusCode(), 201);
		System.out.println(resCode1);
		JsonPath jpath= response.jsonPath();
		int uid=jpath.get("id");
		System.out.println("UserID :"+"  "+uid);
		resBody1=response.getBody().asString();
	    System.out.println(resBody1);
		System.out.println("New User Created");
	    
	    response = alm.PutMethod(uid, "Bravo1", "Male");
	    Assert.assertEquals(response.getStatusCode(), 200);
	    System.out.println(response.getStatusCode());
	    jpath = response.jsonPath();
	    String usrName=jpath.get("name");
	    System.out.println(usrName);
	    Assert.assertEquals(usrName, "Bravo1");
	    System.out.println("User Updated");
	    
	    response= alm.deleteMethod(uid);
	    System.out.println(response.getStatusCode());
	    Assert.assertEquals(response.getStatusCode(), 204);
	    System.out.println("User Deleted");
	    	    
	        
	    response= alm.getSingleEmpMethod(uid);
	    Assert.assertEquals(response.getStatusCode(), 404);
	    System.out.println(response.getStatusCode());
	    
	    
	    
		System.out.println(resBody1);
	}
}
