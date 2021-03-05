//package test;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.springframework.jdbc.core.RowMapper;
//
//
//public class CustomerMapper implements RowMapper<Customer>
//{
//	@Override
//	public Customer mapRow(final ResultSet resultSet, final int i) throws SQLException
//	{
//		final Customer customer = new Customer();
//		customer.setId(resultSet.getInt("id"));
//		customer.setBirthdayDate(resultSet.getDate("birthday_date"));
//		customer.setPhoneNumber(resultSet.getString("phone_number"));
//		customer.setFirstName(resultSet.getString("first_name"));
//		customer.setLastName(resultSet.getString("last_name"));
//		customer.setGender(Gender.valueOf(resultSet.getString("gender")));
//		customer.setPassword(resultSet.getString("password"));
//		customer.setEmail(resultSet.getString("email"));
//		return customer;
//	}
//}
