package ua.com.aimprosoft.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import ua.com.aimprosoft.shop.dao.CustomerDao;
import ua.com.aimprosoft.shop.database.DataSource;
import ua.com.aimprosoft.shop.database.impl.HikariDataSourceImpl;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.util.Extractor;


public class CustomerDaoImpl implements CustomerDao
{
	private final DataSource dataSource;

	public CustomerDaoImpl()
	{
		this.dataSource = new HikariDataSourceImpl();
	}

	private static final String INSERT = "INSERT INTO customer "
			+ "(email, password, first_name, last_name, gender, birthday_date, phone_number) "
			+ "values (?, ?, ?, ?, ?, ?, ?)";
	private static final String FIND_BY_EMAIL = "SELECT * FROM customer WHERE email = ?";

	@Override
	public Optional<Customer> findCustomerByEmail(final String email)
	{
		Customer customer = null;
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement pStatement = connection.prepareStatement(FIND_BY_EMAIL))
		{
			pStatement.setString(1, email);
			final ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next())
			{
				customer = Extractor.mapCustomer(resultSet);
			}
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
		final Optional<Customer> optional = Optional.ofNullable(customer);
		return optional;
	}

	@Override
	public boolean insertCustomer(final Customer customer)
	{
		boolean result = false;
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement pStatement = connection.prepareStatement(INSERT))
		{
			connection.setAutoCommit(false);
			pStatement.setString(1, customer.getEmail());
			pStatement.setString(2, customer.getPassword());
			pStatement.setString(3, customer.getFirstName());
			pStatement.setString(4, customer.getLastName());
			pStatement.setString(5, customer.getGender().name());
			pStatement.setDate(6, new java.sql.Date(customer.getBirthdayDate().getTime()));
			pStatement.setString(7, customer.getPhoneNumber());
			result = pStatement.execute();
			connection.commit();
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
