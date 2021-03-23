package com.restapi.QA.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class TestBase {
	
	public static Properties prop;
	
	public TestBase() throws Throwable

	{
		prop=new Properties();
		FileInputStream ip=new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ECNew\\RestAPI1\\"
				+ "src\\main\\java\\com\\restapi\\QA\\config\\config.properties");
		prop.load(ip);
	}
}
