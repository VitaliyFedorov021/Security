package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DAO
{
	private final DataSource dataSource;

	@Autowired
	public DAO(final DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	public List<Customer> findAll()
	{
		List<Customer> customers = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM customer"))
		{
			ResultSet rSet = pStatement.executeQuery();
			while (rSet.next())
			{
				customers.add(mapCustomer(rSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return customers;
	}

	private Customer mapCustomer(ResultSet resultSet) throws SQLException
	{
		final Customer customer = new Customer();
		customer.setId(resultSet.getInt("id"));
		customer.setBirthdayDate(resultSet.getDate("birthday_date"));
		customer.setPhoneNumber(resultSet.getString("phone_number"));
		customer.setFirstName(resultSet.getString("first_name"));
		customer.setLastName(resultSet.getString("last_name"));
		customer.setGender(Gender.valueOf(resultSet.getString("gender")));
		customer.setPassword(resultSet.getString("password"));
		customer.setEmail(resultSet.getString("email"));
		return customer;
	}
}
