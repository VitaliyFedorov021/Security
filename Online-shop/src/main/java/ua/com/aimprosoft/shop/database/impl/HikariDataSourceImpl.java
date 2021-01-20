package ua.com.aimprosoft.shop.database.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import ua.com.aimprosoft.shop.database.DataSource;
import ua.com.aimprosoft.shop.util.PropertiesReader;


public class HikariDataSourceImpl implements DataSource
{
	private static HikariConfig config;
	private static HikariDataSource dataSource;

	private static HikariDataSource initDataSource() {
		final Properties properties = PropertiesReader.readProperties();
		config = new HikariConfig();
		config.setJdbcUrl(properties.getProperty("jdbcUrl"));
		config.setUsername(properties.getProperty("username"));
		config.setPassword(properties.getProperty("password"));
		config.setDataSourceProperties(properties);
		dataSource = new HikariDataSource(config);
		return dataSource;
	}
	static {
		initDataSource();
	}

	@Override
	public Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
}
