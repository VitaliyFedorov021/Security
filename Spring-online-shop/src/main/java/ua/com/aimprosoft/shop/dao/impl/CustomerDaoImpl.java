package ua.com.aimprosoft.shop.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.com.aimprosoft.shop.dao.CustomerDao;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.mappers.CustomerMapper;


@Component
public class CustomerDaoImpl implements CustomerDao
{
	private final JdbcTemplate jdbcTemplate;
	private static final String FIND_BY_EMAIL = "SELECT * FROM customer WHERE email = ?";
	private static final String INSERT_CUSTOMER = "INSERT INTO customer "
			+ "(email, password, first_name, last_name, gender, birthday_date, phone_number)"
			+ " values (?, ?, ?, ?, ?, ?, ?)";

	@Autowired
	public CustomerDaoImpl(final JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertCustomer(final Customer customer)
	{
		Object[] values = {customer.getEmail(), customer.getPassword(),
		customer.getFirstName(), customer.getLastName(), customer.getGender().name(),
		new java.sql.Date(customer.getBirthdayDate().getTime()), customer.getPhoneNumber()};
		jdbcTemplate.update(INSERT_CUSTOMER, values);
	}

	@Override
	public Optional<Customer> findByEmail(String email)
	{
		try
		{
			return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_EMAIL, new CustomerMapper(), email));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
}
