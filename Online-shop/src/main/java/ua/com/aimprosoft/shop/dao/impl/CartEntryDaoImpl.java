package ua.com.aimprosoft.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.com.aimprosoft.shop.dao.CartEntryDao;
import ua.com.aimprosoft.shop.database.DataSource;
import ua.com.aimprosoft.shop.database.impl.HikariDataSourceImpl;
import ua.com.aimprosoft.shop.models.Address;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.models.Category;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.models.Gender;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class CartEntryDaoImpl implements CartEntryDao
{
	private final DataSource dataSource;

	public CartEntryDaoImpl()
	{
		this.dataSource = new HikariDataSourceImpl();
	}

	public static final String ADD_ENTRY = "INSERT INTO cartEntry "
			+ "(entry_number, quantity, total_price, cart_id, product_id) "
			+ "VALUES (?, ?, ?, ?, ?)";
	public static final String UPDATE_ENTRY = "UPDATE cartEntry SET entry_number = ?, quantity = ?,"
			+ " total_price = ?, cart_id = ?, product_id = ? WHERE id = ?";
	public static final String DELETE_ENTRY = "DELETE FROM cartEntry WHERE id = ?";
	public static final String GET_ENTRIES = "SELECT ce.id, entry_number, quantity AS p_q, ce.total_price,"
			+ " c2.id as c2_id, c2.code as c2_code, c2.total_price as c2_total_price, placed_date,"
			+ " p.id as p_id, p.code as p_code, p.name as p_name, price, description, c2.id, c.id as c_id, c.code as c_code, c.name as c_name, "
			+ "cus.id as cus_id, cus.email, "
			+ "cus.birthday_date, cus.first_name, cus.gender, cus.last_name, cus.password, cus.phone_number, "
			+ "a.id as a_id, street, postal_code, town, region, country FROM cartEntry ce "
			+ "JOIN cart c2 on c2.id = ce.cart_id "
			+ "JOIN product p on p.id = ce.product_id "
			+ "JOIN category c on p.category_id = c.id "
			+ "JOIN customer cus ON c2.customer_id = cus.id LEFT JOIN address a "
			+ "ON c2.address_id = a.id WHERE ce.cart_id = ?";

	@Override
	public boolean insertEntry(final CartEntry cartEntry)
	{
		boolean result = false;
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			final PreparedStatement pStatement = connection.prepareStatement(ADD_ENTRY);
			pStatement.setInt(1, cartEntry.getEntryNumber());
			pStatement.setInt(2, cartEntry.getQuantity());
			pStatement.setDouble(3, cartEntry.getTotalPrice());
			pStatement.setInt(4, cartEntry.getCart().getId());
			pStatement.setInt(5, cartEntry.getProduct().getId());
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
	public boolean updateEntry(final CartEntry cartEntry)
	{
		boolean result = false;
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			final PreparedStatement pStatement = connection.prepareStatement(UPDATE_ENTRY, 1);
			pStatement.setInt(1, cartEntry.getEntryNumber());
			pStatement.setInt(2, cartEntry.getQuantity());
			pStatement.setDouble(3, cartEntry.getTotalPrice());
			pStatement.setInt(4, cartEntry.getCart().getId());
			pStatement.setInt(5, cartEntry.getProduct().getId());
			pStatement.setInt(6, cartEntry.getId());
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
	public List<CartEntry> findEntriesByCartId(final int cartId)
	{
		final List<CartEntry> cartEntries = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement pStatement = connection.prepareStatement(GET_ENTRIES);)
		{
			pStatement.setInt(1, cartId);
			final ResultSet rSet = pStatement.executeQuery();
			while (rSet.next())
			{
				cartEntries.add(mapCartEntry(rSet));
			}
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
		return cartEntries;
	}

	@Override
	public boolean deleteEntry(final CartEntry cartEntry)
	{
		boolean res = false;
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			final PreparedStatement pStatement = connection.prepareStatement(DELETE_ENTRY);
			pStatement.setInt(1, cartEntry.getId());
			res = pStatement.execute();
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
		return res;
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

	private CartEntry mapCartEntry(final ResultSet rSet) throws SQLException
	{
		final CartEntry cartEntry = new CartEntry();
		cartEntry.setEntryNumber(rSet.getInt(ApplicationConstant.ENTRY_NUMBER));
		cartEntry.setQuantity(rSet.getInt(ApplicationConstant.PRODUCT_QUANTITY));
		cartEntry.setId(rSet.getInt(ApplicationConstant.ID));
		cartEntry.setTotalPrice(rSet.getDouble(ApplicationConstant.TOTAL_PRICE));
		final Cart cart = new Cart();
		cart.setId(rSet.getInt(ApplicationConstant.C2_ID));
		cart.setCode(rSet.getString(ApplicationConstant.C2_CODE));
		cart.setPlacedDate(rSet.getDate(ApplicationConstant.PLACED_DATE));
		cart.setTotalPrice(rSet.getDouble(ApplicationConstant.C2_TOTAL_PRICE));
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
		cartEntry.setCart(cart);
		final Product product = new Product();
		product.setId(rSet.getInt(ApplicationConstant.P_ID));
		product.setPrice(rSet.getInt(ApplicationConstant.PRICE));
		product.setName(rSet.getString(ApplicationConstant.P_NAME));
		product.setDescription(rSet.getString(ApplicationConstant.DESCRIPTION));
		product.setCode(rSet.getString(ApplicationConstant.P_CODE));
		final Category category = new Category();
		category.setId(rSet.getInt(ApplicationConstant.C_ID));
		category.setName(rSet.getString(ApplicationConstant.C_NAME));
		category.setCode(rSet.getString(ApplicationConstant.C_CODE));
		product.setCategory(category);
		cartEntry.setProduct(product);
		return cartEntry;
	}
}
