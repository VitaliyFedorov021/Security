package ua.com.aimprosoft.shop.database;

import java.sql.Connection;
import java.sql.SQLException;


public interface DataSource
{
	Connection getConnection() throws SQLException;
}
