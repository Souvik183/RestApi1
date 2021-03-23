package com.restapi.QA.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.restapi.QA.testbase.TestBase;

import io.github.bonigarcia.wdm.online.HttpClient;

public class RestClient extends TestBase {

	public RestClient() throws Throwable {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//GET Method
	/*public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
		
		return closebaleHttpResponse;
			
		}*/
	
	//GET Method without headers
	public CloseableHttpResponse getMethod(String url) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient=HttpClients.createDefault(); // Create korbe HTTPClient. Client Connection banabe method ta 
		HttpGet httpget=new HttpGet(url);// GET request jache with url 0.
		CloseableHttpResponse response= httpClient.execute(httpget);// GET click er por hitting the API
		
		return response;
	}
	
	//GET Method with headers. Extra Hashmap add korteb hbe to make key value pair
		public CloseableHttpResponse getMethod(String url, HashMap<String, String> headermap) throws ClientProtocolException, IOException
		{
			CloseableHttpClient httpClient=HttpClients.createDefault(); // Create korbe HTTPClient. Client Connection banabe method ta 
			HttpGet httpget=new HttpGet(url);// GET request jache with url 0.
			//URL deoar por header add korte hbe
			
			for(Map.Entry<String, String> entry : headermap.entrySet())
			{
				httpget.addHeader(entry.getKey(), entry.getValue());
			}
			
			CloseableHttpResponse response= httpClient.execute(httpget);// GET click er por hitting the API
			
			return response;
		}
		
	//POST Method Call
		//Entity ta hche payload. Mane body
		public CloseableHttpResponse postMethod(String url, String entityString, HashMap<String, String> headermap) throws ClientProtocolException, IOException
		{
			CloseableHttpClient httpClient=HttpClients.createDefault();
			
			//3te jinis hobe
			// POST select with URL
			
			HttpPost httppost=new HttpPost(url);
			
			//ebar entity pass korate hbe
			httppost.setEntity(new StringEntity(entityString)); //setEntity method use hoy to define payload
			
			//For Headers
			for(Map.Entry<String, String> entry : headermap.entrySet())
			{
				httppost.addHeader(entry.getKey(), entry.getValue());
			}
			
			//ebar send e click korbo
			CloseableHttpResponse response=httpClient.execute(httppost);
			return response;
		}

}
