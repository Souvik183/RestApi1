package com.restapi.QA.testcase;

import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.restapi.QA.TestUtil.TestUtil;
import com.restapi.QA.client.RestClient;
import com.restapi.QA.testbase.TestBase;

public class GETAPITest extends TestBase{
	TestBase testbase;
	RestClient restclient;
	String baseURL;
	String apiURL;
	String exactURL;
	CloseableHttpResponse response; 
	
	public GETAPITest() throws Throwable {
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
	
	@Test(priority =1)
	public void test1ForGETWithoutHeader() throws Throwable
	{
		restclient=new RestClient();
		response=restclient.getMethod(exactURL);
		
				//Response Code getting(Status Code)
				int responsecode=response.getStatusLine().getStatusCode(); // status code ta ki seta dekhchi. 200 500 seta
				System.out.println(responsecode);
				Assert.assertEquals(responsecode, TestUtil.Response_Code, "Status Code not matched");
				
				//Response getting(Full Details)
				String responseData=EntityUtils.toString(response.getEntity(), "UTF-8");
				JSONObject responseJSON=new JSONObject(responseData);// String take json e convert korchi
				System.out.println("Response JSON is : "+responseJSON);
				
				//This is for JSON Object
				String perpagevalue=TestUtil.getValueByJPath(responseJSON, "per_page");
				System.out.println("Value of Per page value : "+perpagevalue); // erom kore sab attribute gulo paoa jabe
				Assert.assertEquals(Integer.parseInt(perpagevalue), 6);// cz String compare hche int er sathe
				
				//This is for JSON Array
				String email=TestUtil.getValueByJPath(responseJSON, "/data[0]/email");
				System.out.println("Value of Last Name : "+email);
				
				
				//Header paoa
				Header[] headerarray = response.getAllHeaders();
				HashMap<String, String> allheaders=new HashMap<String, String>();
				for(Header header : headerarray)
				{
					allheaders.put(header.getName(), header.getValue());
				}
				System.out.println("Headers details are : " +allheaders);
	}
	
	@Test(priority = 2)
	public void test1ForGETWithHeader() throws Throwable
	{
		restclient=new RestClient();
		
		
		HashMap<String, String> headermaps=new HashMap<String, String>();
		headermaps.put("content-type", "application/json");
		//header.put("username", "admin");
		
		
		response=restclient.getMethod(exactURL,headermaps);
		
				//Response Code getting(Status Code)
				int responsecode=response.getStatusLine().getStatusCode(); // status code ta ki seta dekhchi. 200 500 seta
				System.out.println(responsecode);
				Assert.assertEquals(responsecode, TestUtil.Response_Code, "Status Code not matched");
				
				//Response getting(Full Details)
				String responseData=EntityUtils.toString(response.getEntity(), "UTF-8");
				JSONObject responseJSON=new JSONObject(responseData);// String take json e convert korchi
				System.out.println("Response JSON is : "+responseJSON);
				
				//This is for JSON Object
				String perpagevalue=TestUtil.getValueByJPath(responseJSON, "per_page");
				System.out.println("Value of Per page value : "+perpagevalue); // erom kore sab attribute gulo paoa jabe
				Assert.assertEquals(Integer.parseInt(perpagevalue), 6);// cz String compare hche int er sathe
				
				//This is for JSON Array
				String email=TestUtil.getValueByJPath(responseJSON, "/data[0]/email");
				System.out.println("Value of Last Name : "+email);
				
				
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
