package ua.com.aimprosoft.shop.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.com.aimprosoft.shop.dao.CartEntryDao;
import ua.com.aimprosoft.shop.entities.CartEntry;
import ua.com.aimprosoft.shop.mappers.CartEntryMapper;
import ua.com.aimprosoft.shop.mappers.IntegerMapper;


@Component
public class CartEntryDaoImpl implements CartEntryDao
{
	private final JdbcTemplate jdbcTemplate;
	private static final String ADD_ENTRY = "INSERT INTO cartEntry "
			+ "(entry_number, quantity, total_price, cart_id, product_id) "
			+ "VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_ENTRY = "UPDATE cartEntry SET quantity = ?,"
			+ " total_price = ? WHERE id = ?";
	private static final String GET_ENTRIES =
			"SELECT cartEntry.id, entry_number, quantity AS product_quantity, cartEntry.total_price,"
					+ " product.id as product_id, product.code as product_code, product.name as product_name, price, description FROM cartEntry "
					+ "JOIN product on product.id = cartEntry.product_id "
					+ "JOIN cart on cart.id = cartEntry.cart_id "
					+ "WHERE cart.code = ?";
	private static final String GET_ENTRY_BY_PRODUCT_CODE =
			"SELECT cartEntry.id, entry_number, quantity AS product_quantity, cartEntry.total_price,"
					+ " product.id as product_id, product.code as product_code, product.name as product_name, price, description "
					+ "FROM cartEntry "
					+ "JOIN product ON cartEntry.product_id = product.id "
					+ "JOIN cart on cart.id = cartEntry.cart_id "
					+ "WHERE product.code = ? AND cart.code = ?";
	private static final String CURRENT_ENTRY_NUMBER = "SELECT MAX(cartEntry.entry_number) as current_entry "
			+ "FROM cartEntry JOIN cart ON cart.id = cartEntry.cart_id WHERE cart.code = ?";
	private static final String DELETE_ENTRY = "DELETE FROM cartEntry WHERE id = ?";

	@Autowired
	public CartEntryDaoImpl(final JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertEntry(final CartEntry cartEntry)
	{
		final Object[] values = {cartEntry.getEntryNumber(),
				cartEntry.getQuantity(),
				cartEntry.getTotalPrice(),
				cartEntry.getCart().getId(), cartEntry.getProduct().getId()};
		jdbcTemplate.update(ADD_ENTRY, values);
	}

	@Override
	public void updateEntry(final CartEntry cartEntry)
	{
		final Object[] values = {cartEntry.getQuantity(),
				cartEntry.getTotalPrice(), cartEntry.getId()};
		jdbcTemplate.update(UPDATE_ENTRY, values);
	}

	@Override
	public List<CartEntry> findEntriesByCartCode(final String cartCode)
	{
		return jdbcTemplate.query(GET_ENTRIES, new CartEntryMapper(), cartCode);
	}

	@Override
	public Optional<CartEntry> findByProductCode(final String productCode, final String cartCode)
	{
		try
		{
			final Object[] values = { productCode, cartCode };
			return Optional.of(jdbcTemplate.queryForObject(GET_ENTRY_BY_PRODUCT_CODE, values, new CartEntryMapper()));
		}
		catch (final EmptyResultDataAccessException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public int findCurrentEntryNumber(final String cartCode)
	{
		return jdbcTemplate.queryForObject(CURRENT_ENTRY_NUMBER, new IntegerMapper(), cartCode);
	}

	@Override
	public void deleteEntry(final int id)
	{
		jdbcTemplate.update(DELETE_ENTRY, id);
	}
}
