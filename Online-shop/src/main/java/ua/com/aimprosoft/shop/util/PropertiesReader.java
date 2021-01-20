package ua.com.aimprosoft.shop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesReader
{
	public static Properties readProperties()
	{
		final Properties properties = new Properties();
		try (final InputStream reader = PropertiesReader.class.getClassLoader().getResourceAsStream("hikariDB.properties"))
		{
			properties.load(reader);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		return properties;
	}
}
