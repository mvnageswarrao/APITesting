package com.vp.restutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.mozilla.javascript.ast.WhileLoop;

import com.opencsv.CSVReader;

public class TestBase {
	
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_201 = 201;
	
	public Properties prop;
	public TestBase()
	{
		try
		{
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\vp\\restutils\\config.properties");
			prop.load(ip);		
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public List<String> TestData() throws IOException
	{
		
		
			CSVReader reader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\src\\test\\java\\com\\vp\\restutils\\TestData.CSV"));
			String[] cell;
			List<String> FullData = new ArrayList<String>(); 
			while ((cell=reader.readNext())!=null)
			{			
				for(int i=0;i<cell.length;i++)
				{
					FullData.add(cell[i]);
				}
			}
		return FullData;
	}

}
