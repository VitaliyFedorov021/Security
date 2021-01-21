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
	private static HikariDataSource dataSource;

	public HikariDataSourceImpl()
	{
		final Properties properties = PropertiesReader.readProperties(ApplicationConstant.DB_PROPERTIES);
		final HikariConfig config = new HikariConfig();
		config.setJdbcUrl(properties.getProperty("jdbcUrl"));
		config.setUsername(properties.getProperty("username"));
		config.setPassword(properties.getProperty("password"));
		config.setDataSourceProperties(properties);
		dataSource = new HikariDataSource(config);
	}

	@Override
	public Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
}
