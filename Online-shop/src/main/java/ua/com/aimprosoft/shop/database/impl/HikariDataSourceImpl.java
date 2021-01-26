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
		config.setJdbcUrl(properties.getProperty(ApplicationConstant.JDBC_URL));
		config.setUsername(properties.getProperty(ApplicationConstant.USERNAME));
		config.setPassword(properties.getProperty(ApplicationConstant.PASSWORD));
		config.setDataSourceProperties(properties);
		dataSource = new HikariDataSource(config);
	}

	@Override
	public Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
}
