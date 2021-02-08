package ua.com.aimprosoft.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.com.aimprosoft.shop.dao.AddressDao;
import ua.com.aimprosoft.shop.database.DataSource;
import ua.com.aimprosoft.shop.database.impl.HikariDataSourceImpl;
import ua.com.aimprosoft.shop.models.Address;


public class AddressDaoImpl implements AddressDao
{
	private final DataSource dataSource = HikariDataSourceImpl.getInstance();
	private static final String SAVE_ADDRESS = "INSERT INTO address (street, postal_code, town, region, country)"
			+ "VALUES (?, ?, ?, ?, ?)";

	@Override
	public void insertAddress(final Address address)
	{
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			final PreparedStatement pStatement = connection.prepareStatement(SAVE_ADDRESS,
					Statement.RETURN_GENERATED_KEYS);
			connection.setAutoCommit(false);
			pStatement.setString(1, address.getStreet());
			pStatement.setString(2, address.getPostalCode());
			pStatement.setString(3, address.getTown());
			pStatement.setString(4, address.getRegion());
			pStatement.setString(5, address.getCountry());
			pStatement.executeUpdate();
			final ResultSet keys = pStatement.getGeneratedKeys();
			keys.next();
			address.setId(keys.getInt(1));
			connection.commit();
		}
		catch (final SQLException e)
		{
			rollback(connection);
		}
		finally
		{
			close(connection);
		}
	}

	private void close(final Connection connection)
	{
		try
		{
			if (connection != null)
			{
				connection.close();
			}
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
	}

	private void rollback(final Connection connection)
	{
		try
		{
			if (connection != null)
			{
				connection.rollback();
			}
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
	}
}
