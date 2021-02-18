package ua.com.aimprosoft.shop.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import ua.com.aimprosoft.shop.dao.AddressDao;
import ua.com.aimprosoft.shop.models.Address;

@Component
public class AddressDaoImpl implements AddressDao
{
	private final JdbcTemplate jdbcTemplate;
	private static final String SAVE_ADDRESS = "INSERT INTO address (street, postal_code, town, region, country)"
			+ "VALUES (?, ?, ?, ?, ?)";

	@Autowired
	public AddressDaoImpl(final JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertAddress(final Address address)
	{
		PreparedStatementCreator psc = connection -> {
			PreparedStatement pStatement = connection.prepareStatement(SAVE_ADDRESS, Statement.RETURN_GENERATED_KEYS);
			pStatement.setString(1, address.getStreet());
			pStatement.setString(2, address.getPostalCode());
			pStatement.setString(3, address.getTown());
			pStatement.setString(4, address.getRegion());
			pStatement.setString(5, address.getCountry());
			return pStatement;
		};
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		address.setId(keyHolder.getKey().intValue());
	}
}
