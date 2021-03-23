package com.restapi.QA.TestUtil;


import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtil {
	
	public static int Response_Code=200;
	
	public static String getValueByJPath(JSONObject responsejson, String jpath){
		Object obj = responsejson;
		for(String s : jpath.split("/")) 
			if(!s.isEmpty()) 
				if(!(s.contains("[") || s.contains("]"))) // For JSON Object
					obj = ((JSONObject) obj).get(s);
				else if(s.contains("[") || s.contains("]"))// For JSON Array
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
		return obj.toString();
	}

}
