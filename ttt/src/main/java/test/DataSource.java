package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:jdbc.properties")
public class DataSource
{
	@Value("${jdbcDriver}")
	private static String driver;
	@Value("${jdbcUrl}")
	private String url;
	@Value("${username}")
	private String username;
	@Value("${password}")
	private String password;
	static {
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url, "root", password);
	}
}
