package ua.com.shop.aimprosoft.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesReader
{
	public static Properties readProperties()
	{
		Properties properties = new Properties();
		try (InputStream reader = PropertiesReader.class.getClassLoader().getResourceAsStream("hikariDB.properties"))
		{
			properties.load(reader);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return properties;
	}
}
