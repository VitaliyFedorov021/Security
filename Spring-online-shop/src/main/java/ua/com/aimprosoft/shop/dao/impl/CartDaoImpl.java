package ua.com.aimprosoft.shop.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import ua.com.aimprosoft.shop.dao.CartDao;
import ua.com.aimprosoft.shop.entities.Address;
import ua.com.aimprosoft.shop.entities.Cart;
import ua.com.aimprosoft.shop.mappers.CartMapper;


@Component
public class CartDaoImpl implements CartDao
{
	private final JdbcTemplate jdbcTemplate;

	private static final String ADD_TO_CART =
			"INSERT INTO cart (code, total_price, customer_id)"
					+ " values(?, ?, ?)";
	private static final String UPDATE_CART = "UPDATE cart SET total_price = ?, placed_date = ?, address_id = ? WHERE id = ?";
	private static final String FIND_ACTIVE_CART =
			"SELECT cart.id, cart.code, cart.total_price, cart.placed_date FROM cart "
					+ "JOIN customer on customer.id = cart.customer_id "
					+ "WHERE email = ? AND cart.placed_date IS NULL";
	public static final String FIND_CART_BY_CODE =
			"SELECT * FROM cart WHERE code = ?";

	@Autowired
	public CartDaoImpl(final JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertCart(final Cart cart)
	{
		final PreparedStatementCreator psc = connection -> getPreparedStatement(cart, connection);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		final int id = keyHolder.getKey().intValue();
		cart.setId(id);
	}

	private PreparedStatement getPreparedStatement(final Cart cart, final java.sql.Connection connection)
			throws SQLException
	{
		final PreparedStatement pStatement = connection.prepareStatement(ADD_TO_CART, Statement.RETURN_GENERATED_KEYS);
		pStatement.setString(1, cart.getCode());
		pStatement.setDouble(2, cart.getTotalPrice());
		pStatement.setInt(3, cart.getCustomer().getId());
		return pStatement;
	}

	@Override
	public Optional<Cart> findActiveCart(final String customerEmail)
	{
		try
		{
			return Optional.of(jdbcTemplate.queryForObject(FIND_ACTIVE_CART, new CartMapper(), customerEmail));
		}
		catch (final Exception e)
		{
			return Optional.empty();
		}
	}

	@Override
	public void updateCart(final Cart cart)
	{
		final Object[] values = {cart.getTotalPrice(),
				getDate(cart.getPlacedDate()),
				getAddressId(cart.getDeliveryAddress()),
				cart.getId()};
		jdbcTemplate.update(UPDATE_CART, values);
	}

	@Override
	public Optional<Cart> findCartByCode(final String cartCode)
	{
		try
		{
			return Optional.of(jdbcTemplate.queryForObject(FIND_CART_BY_CODE, new CartMapper(), cartCode));
		}
		catch (final Exception e)
		{
			return Optional.empty();
		}
	}

	private Object getAddressId(final Address address) {
		if (address != null) {
			return address.getId();
		}
		return null;
	}

	private Object getDate(final Date date) {
		if (date != null) {
			return new java.sql.Date(date.getTime());
		}
		return null;
	}
}
