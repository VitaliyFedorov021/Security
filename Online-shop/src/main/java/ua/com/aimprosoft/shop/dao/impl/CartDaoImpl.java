package ua.com.aimprosoft.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import ua.com.aimprosoft.shop.dao.CartDao;
import ua.com.aimprosoft.shop.database.DataSource;
import ua.com.aimprosoft.shop.database.impl.HikariDataSourceImpl;
import ua.com.aimprosoft.shop.models.Address;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class CartDaoImpl implements CartDao
{
	private final DataSource dataSource;

	public CartDaoImpl()
	{
		this.dataSource = HikariDataSourceImpl.getInstance();
	}

	private static final String ADD_TO_CART =
			"INSERT INTO cart (code, total_price, customer_id)"
					+ " values(?, ?, ?)";
	private static final String UPDATE_CART = "UPDATE cart SET total_price = ?, placed_date = ?, address_id = ? WHERE id = ?";
	private static final String FIND_CART_BY_EMAIL =
			"SELECT cart.id, cart.code, cart.total_price, cart.placed_date, customer.id as customer_id, customer.email FROM cart "
					+ "JOIN customer ON cart.customer_id = customer.id "
					+ "WHERE customer.email = ?";
	private static final String FIND_ACTIVE_CART =
			"SELECT cart.id, cart.code, cart.total_price, cart.placed_date FROM cart "
					+ "JOIN customer on customer.id = cart.customer_id "
					+ "WHERE email = ? AND cart.placed_date IS NULL";
	private static final String FIND_CART_BY_CODE =
			"SELECT cart.id, cart.code, cart.total_price, cart.placed_date, "
					+ "country, town, street, region, postal_code, address_id FROM cart "
					+ "LEFT JOIN address ON cart.address_id = address.id "
					+ "WHERE cart.code = ?";

	@Override
	public boolean insertCart(final Cart cart)
	{
		boolean result = false;
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			final PreparedStatement pStatement = connection.prepareStatement(ADD_TO_CART,
					Statement.RETURN_GENERATED_KEYS);
			connection.setAutoCommit(false);
			pStatement.setString(1, cart.getCode());
			pStatement.setDouble(2, cart.getTotalPrice());
			pStatement.setInt(3, cart.getCustomer().getId());
			result = pStatement.execute();
			final ResultSet id = pStatement.getGeneratedKeys();
			id.next();
			cart.setId(id.getInt(1));
			connection.commit();
		}
		catch (final SQLException e)
		{
			rollback(connection);
			e.printStackTrace();
		}
		finally
		{
			close(connection);
		}
		return result;
	}

	@Override
	public List<Cart> findCartsByCustomerEmail(final String customerEmail)
	{
		final List<Cart> carts = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement pStatement = connection.prepareStatement(FIND_CART_BY_EMAIL))
		{
			pStatement.setString(1, customerEmail);
			final ResultSet rSet = pStatement.executeQuery();
			while (rSet.next())
			{
				carts.add(mapCart(rSet));
			}
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
		return carts;
	}

	@Override
	public Optional<Cart> findActiveCart(final String customerEmail)
	{
		Cart cart = null;
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement pStatement = connection.prepareStatement(FIND_ACTIVE_CART))
		{
			pStatement.setString(1, customerEmail);
			final ResultSet rSet = pStatement.executeQuery();
			if (rSet.next())
			{
				cart = mapCart(rSet);
			}
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
		return Optional.ofNullable(cart);
	}

	@Override
	public Cart findCartByCode(final String cartCode)
	{
		Cart cart = null;
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement pStatement = connection.prepareStatement(FIND_CART_BY_CODE))
		{
			pStatement.setString(1, cartCode);
			final ResultSet rSet = pStatement.executeQuery();
			if (rSet.next())
			{
				cart = mapCart(rSet);
				cart.setDeliveryAddress(mapAddress(rSet));
			}
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
		return cart;
	}

	@Override
	public boolean updateCart(final Cart cart)
	{
		final boolean result = false;
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			final PreparedStatement pStatement = connection.prepareStatement(UPDATE_CART);
			connection.setAutoCommit(false);
			pStatement.setDouble(1, cart.getTotalPrice());
			Address address = cart.getDeliveryAddress();
			Date date = cart.getPlacedDate();
			pStatement.setObject(2, getDate(date));
			pStatement.setObject(3, getAddressId(address));
			pStatement.setInt(4, cart.getId());
			pStatement.executeUpdate();
			connection.commit();
		}
		catch (final SQLException e)
		{
			rollback(connection);
			e.printStackTrace();
		}
		finally
		{
			close(connection);
		}
		return result;
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

	private Cart mapCart(final ResultSet rSet) throws SQLException
	{
		final Cart cart = new Cart();
		cart.setId(rSet.getInt(ApplicationConstant.ID));
		cart.setCode(rSet.getString(ApplicationConstant.CODE));
		cart.setPlacedDate(rSet.getDate(ApplicationConstant.PLACED_DATE));
		cart.setTotalPrice(rSet.getDouble(ApplicationConstant.TOTAL_PRICE));
		return cart;
	}

	private Address mapAddress(final ResultSet rSet) throws SQLException
	{
		final Address address = new Address();
		address.setStreet(rSet.getString(ApplicationConstant.STREET));
		address.setTown(rSet.getString(ApplicationConstant.TOWN));
		address.setRegion(rSet.getString(ApplicationConstant.REGION));
		address.setPostalCode(rSet.getString(ApplicationConstant.POSTAL_CODE_DB));
		address.setCountry(rSet.getString(ApplicationConstant.COUNTRY));
		address.setId(rSet.getInt(ApplicationConstant.ADDRESS_ID));
		return address;
	}
}
