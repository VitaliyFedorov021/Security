package ua.com.aimprosoft.shop.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.models.Gender;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class CustomerMapper implements RowMapper<Customer>
{

	@Override
	public Customer mapRow(final ResultSet resultSet, final int i) throws SQLException
	{
		Customer customer = new Customer();
		customer.setId(resultSet.getInt(ApplicationConstant.ID));
		customer.setBirthdayDate(resultSet.getDate(ApplicationConstant.BIRTHDAY_DATE_DB));
		customer.setPhoneNumber(resultSet.getString(ApplicationConstant.PHONE_NUMBER_DB));
		customer.setFirstName(resultSet.getString(ApplicationConstant.FIRST_NAME_DB));
		customer.setLastName(resultSet.getString(ApplicationConstant.LAST_NAME_DB));
		customer.setGender(Gender.valueOf(resultSet.getString(ApplicationConstant.GENDER)));
		customer.setPassword(resultSet.getString(ApplicationConstant.PASSWORD));
		customer.setEmail(resultSet.getString(ApplicationConstant.EMAIL));
		return customer;
	}
}
