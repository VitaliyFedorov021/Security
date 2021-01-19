package ua.com.shop.aimprosoft.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.com.shop.aimprosoft.database.DataSource;
import ua.com.shop.aimprosoft.database.dao.CustomerDao;
import ua.com.shop.aimprosoft.models.Customer;
import ua.com.shop.aimprosoft.models.Gender;


public class CustomerDaoImpl implements CustomerDao
{
	private final DataSource dataSource;
	private static final String INSERT = "INSERT INTO customer "
			+ "(email, password, first_name, last_name, gender, birthday_date, phone_number) "
			+ "values (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_LOGIN = "SELECT * FROM customer WHERE email = ?";

	public CustomerDaoImpl(final DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	@Override
	public Customer selectByLogin(final String email)
	{
		Customer customer = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pStatement = connection.prepareStatement(SELECT_LOGIN))
		{
			pStatement.setString(1, email);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				customer = mapCustomer(resultSet);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public boolean insert(final Customer customer)
	{
		boolean result = false;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pStatement = connection.prepareStatement(INSERT))
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
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	private Customer mapCustomer(ResultSet resultSet) throws SQLException
	{
		return new Customer(resultSet.getString("email"), resultSet.getString("password"),
				resultSet.getString("first_name"), resultSet.getString("last_name"),
				Gender.valueOf(resultSet.getString("gender")), resultSet.getDate("birthday_date"),
				resultSet.getString("phone_number"));
	}
}
