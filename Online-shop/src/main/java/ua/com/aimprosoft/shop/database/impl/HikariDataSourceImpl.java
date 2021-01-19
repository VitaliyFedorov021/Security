package ua.com.shop.aimprosoft.database.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.rmi.CORBA.Util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import ua.com.shop.aimprosoft.database.DataSource;
import ua.com.shop.aimprosoft.util.PropertiesReader;


public class HikariDataSourceImpl implements DataSource
{
	private HikariConfig config;
	private HikariDataSource dataSource;

	private HikariDataSource initDataSource() {
		Properties properties = PropertiesReader.readProperties();
		config = new HikariConfig();
		config.setJdbcUrl(properties.getProperty("jdbcUrl"));
		config.setUsername(properties.getProperty("username"));
		config.setPassword(properties.getProperty("password"));
		config.setDataSourceProperties(properties);
		dataSource = new HikariDataSource(config);
		return dataSource;
	}
	@Override
	public Connection getConnection() throws SQLException
	{
		return initDataSource().getConnection();
	}
}
