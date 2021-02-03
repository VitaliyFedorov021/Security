package ua.com.aimprosoft.shop.database.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import ua.com.aimprosoft.shop.database.DataSource;
import ua.com.aimprosoft.shop.util.PropertiesReader;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class HikariDataSourceImpl implements DataSource
{
	private static DataSource instance;
	private final HikariDataSource dataSource;

	public static synchronized DataSource getInstance()
	{
		if (instance == null)
		{
			instance = new HikariDataSourceImpl();
		}
		return instance;
	}

	private HikariDataSourceImpl()
	{
		registerDriver();
		final HikariConfig config = new HikariConfig();
		final Properties properties = PropertiesReader.readProperties(ApplicationConstant.DB_PROPERTIES);
		config.setDataSourceProperties(properties);
		config.setJdbcUrl(properties.getProperty(ApplicationConstant.JDBC_URL));
		config.setUsername(properties.getProperty(ApplicationConstant.USERNAME));
		config.setPassword(properties.getProperty(ApplicationConstant.PASSWORD));
		dataSource = new HikariDataSource(config);
	}

	private void registerDriver()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (final ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
}
