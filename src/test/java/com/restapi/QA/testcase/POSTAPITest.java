package com.restapi.QA.testcase;

import java.io.File;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import com.restapi.QA.Data.Users;
import com.restapi.QA.client.RestClient;
import com.restapi.QA.testbase.TestBase;

public class POSTAPITest extends TestBase{
	
	TestBase testbase;
	RestClient restclient;
	String baseURL;
	String apiURL;
	String exactURL;
	CloseableHttpResponse response;
	Users user;

	public POSTAPITest() throws Throwable {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@BeforeMethod
	public void setup() throws Throwable
	{
		testbase=new TestBase();
		
		 baseURL=prop.getProperty("url");	
		 apiURL=prop.getProperty("serviceurl");
		 exactURL=baseURL+apiURL;
		
	}
	
	@Test
	public void postAPITest() throws Throwable
	{
		restclient=new RestClient();
		HashMap<String, String> headermaps=new HashMap<String, String>();
		headermaps.put("content-type", "application/json");
		
		
		
		//Now User.jave class take json e change korte hbe
		// eta hbe using jackson api to convert java to json & json to java
		ObjectMapper mapper= new ObjectMapper();
		user=new Users("Vik","Testing");
		
		mapper.writeValue(new File("C:\\Users\\User\\eclipse-workspace\\ECNew\\RestAPI1\\src\\main\\java\\com\\restapi\\QA\\Data\\users.json"), user);
		String userjsonString=mapper.writeValueAsString(user);
		System.out.println(userjsonString);
		
		response=restclient.postMethod(exactURL, userjsonString, headermaps);
		
		//Response Code getting(Status Code)
		int responsecode=response.getStatusLine().getStatusCode(); // status code ta ki seta dekhchi. 200 500 seta
		System.out.println(responsecode);
		
		
		//Response getting(Full Details)
		String responseData=EntityUtils.toString(response.getEntity(), "UTF-8");
		JSONObject responseJSON=new JSONObject(responseData);// String take json e convert korchi
		System.out.println("Response JSON is : "+responseJSON);
		
		//Header paoa
		Header[] headerarray = response.getAllHeaders();
		HashMap<String, String> allheaders=new HashMap<String, String>();
		for(Header header : headerarray)
		{
			allheaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers details are : " +allheaders);
		
		
	}
	

}
