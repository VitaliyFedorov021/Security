package ua.com.aimprosoft.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import ua.com.aimprosoft.shop.dao.CartDao;
import ua.com.aimprosoft.shop.database.DataSource;
import ua.com.aimprosoft.shop.database.impl.HikariDataSourceImpl;
import ua.com.aimprosoft.shop.models.Address;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.models.Gender;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class CartDaoImpl implements CartDao
{
	private final DataSource dataSource;

	public CartDaoImpl()
	{
		this.dataSource = new HikariDataSourceImpl();
	}

	private static final String ADD_TO_CART =
			"INSERT INTO cart (code, total_price, placed_date, customer_id, address_id)"
					+ " values(?, ?, ?, ?, ?)";
	private static final String UPDATE_CART = "UPDATE cart SET code = ?, total_price = ?, "
			+ "placed_date = ?, customer_id = ?, address_id = ? WHERE id = ?";
	private static final String DELETE_CART = "DELETE FROM cart WHERE id = ?";
	private static final String FIND_CART =
			"SELECT c.id, c.code, c.total_price, c.placed_date, cus.id as cus_id, cus.email, "
					+ "cus.birthday_date, cus.first_name, cus.gender, cus.last_name, cus.password, cus.phone_number,"
					+ " a.id as a_id, street, postal_code, town, region, country FROM cart c "
					+ "JOIN customer cus ON c.customer_id = cus.id LEFT JOIN address a "
					+ "ON c.address_id = a.id WHERE c.customer_id = ?";

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
			pStatement.setDate(3, new java.sql.Date(cart.getPlacedDate().getTime()));
			pStatement.setInt(4, cart.getCustomer().getId());
			Integer addressId = null;
			if (cart.getDeliveryAddress() != null)
			{
				addressId = cart.getDeliveryAddress().getId();
				pStatement.setInt(5, addressId);
			}
			else
			{
				pStatement.setNull(5, Types.INTEGER);
			}
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
	public List<Cart> findCartByCustomerId(final int customerId)
	{
		final List<Cart> carts = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement pStatement = connection.prepareStatement(FIND_CART))
		{
			pStatement.setInt(1, customerId);
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
	public boolean updateCart(final Cart cart)
	{
		boolean result = false;
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			final PreparedStatement pStatement = connection.prepareStatement(UPDATE_CART);
			connection.setAutoCommit(false);
			pStatement.setString(1, cart.getCode());
			pStatement.setDouble(2, cart.getTotalPrice());
			pStatement.setDate(3, new java.sql.Date(cart.getPlacedDate().getTime()));
			pStatement.setInt(4, cart.getCustomer().getId());
			Integer addressId = null;
			if (cart.getDeliveryAddress() != null)
			{
				addressId = cart.getDeliveryAddress().getId();
				pStatement.setInt(5, addressId);
			}
			else
			{
				pStatement.setNull(5, Types.INTEGER);
			}
			pStatement.setInt(6, cart.getId());
			result = pStatement.execute();
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
	public boolean deleteCart(final Cart cart)
	{
		boolean result = false;
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			final PreparedStatement pStatement = connection.prepareStatement(DELETE_CART);
			connection.setAutoCommit(false);
			pStatement.setInt(1, cart.getId());
			result = pStatement.execute();
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

	private void close(final Connection connection)
	{
		try
		{
			connection.close();
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
			connection.rollback();
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
		final Customer customer = new Customer();
		customer.setId(rSet.getInt(ApplicationConstant.CUS_ID));
		customer.setEmail(rSet.getString(ApplicationConstant.EMAIL));
		customer.setPassword(rSet.getString(ApplicationConstant.PASSWORD));
		customer.setFirstName(rSet.getString(ApplicationConstant.FIRST_NAME));
		customer.setLastName(rSet.getString(ApplicationConstant.LAST_NAME));
		customer.setGender(Gender.valueOf(rSet.getString(ApplicationConstant.GENDER)));
		customer.setBirthdayDate(rSet.getDate(ApplicationConstant.BIRTHDAY));
		customer.setPhoneNumber(rSet.getString(ApplicationConstant.NUMBER));
		cart.setCustomer(customer);
		Address address = null;
		final String country = rSet.getString(ApplicationConstant.COUNTRY);
		if (country != null)
		{
			address = new Address();
			rSet.getInt(ApplicationConstant.A_ID);
			address.setCountry(country);
			address.setPostalCode(rSet.getString(ApplicationConstant.POSTAL_CODE));
			address.setRegion(rSet.getString(ApplicationConstant.REGION));
			address.setTown(rSet.getString(ApplicationConstant.TOWN));
			address.setStreet(rSet.getString(ApplicationConstant.STREET));
			cart.setDeliveryAddress(address);
		}
		cart.setDeliveryAddress(address);
		customer.setCart(cart);
		return cart;
	}
}
