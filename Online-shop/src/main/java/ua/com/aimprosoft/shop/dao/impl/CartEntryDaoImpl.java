package ua.com.aimprosoft.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ua.com.aimprosoft.shop.dao.CartEntryDao;
import ua.com.aimprosoft.shop.database.DataSource;
import ua.com.aimprosoft.shop.database.impl.HikariDataSourceImpl;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class CartEntryDaoImpl implements CartEntryDao
{
	private final DataSource dataSource;

	public CartEntryDaoImpl()
	{
		this.dataSource = HikariDataSourceImpl.getInstance();
	}

	private static final String ADD_ENTRY = "INSERT INTO cartEntry "
			+ "(entry_number, quantity, total_price, cart_id, product_id) "
			+ "VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_ENTRY = "UPDATE cartEntry SET quantity = ?,"
			+ " total_price = ? WHERE id = ?";
	private static final String DELETE_ENTRY = "DELETE FROM cartEntry WHERE id = ?";
	private static final String GET_ENTRIES =
			"SELECT cartEntry.id, entry_number, quantity AS product_quantity, cartEntry.total_price,"
					+ " product.id as product_id, product.code as product_code, product.name as product_name, price, description FROM cartEntry "
					+ "JOIN product on product.id = cartEntry.product_id "
					+ "JOIN cart on cart.id = cartEntry.cart_id "
					+ "WHERE cart.code = ?";
	private static final String GET_ENTRY =
			"SELECT cartEntry.id, entry_number, quantity AS product_quantity, cartEntry.total_price,"
					+ " product.id as product_id, product.code as product_code, product.name as product_name, price, description "
					+ "FROM cartEntry "
					+ "JOIN product ON cartEntry.product_id = product.id "
					+ "WHERE cartEntry.id = ?";
	private static final String GET_ENTRY_BY_PRODUCT_CODE =
			"SELECT cartEntry.id, entry_number, quantity AS product_quantity, cartEntry.total_price,"
					+ " product.id as product_id, product.code as product_code, product.name as product_name, price, description "
					+ "FROM cartEntry "
					+ "JOIN product ON cartEntry.product_id = product.id "
					+ "JOIN cart on cart.id = cartEntry.cart_id "
					+ "WHERE product.code = ? AND cart.code = ?";
	private static final String CURRENT_ENTRY_NUMBER = "SELECT MAX(cartEntry.entry_number) as current_entry "
			+ "FROM cartEntry JOIN cart ON cart.id = cartEntry.cart_id WHERE cart.code = ?";

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
			final PreparedStatement pStatement = connection.prepareStatement(UPDATE_ENTRY);
			pStatement.setInt(1, cartEntry.getQuantity());
			pStatement.setDouble(2, cartEntry.getTotalPrice());
			pStatement.setInt(3, cartEntry.getId());
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
	public List<CartEntry> findEntriesByCartCode(final String cartCode)
	{
		final List<CartEntry> cartEntries = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement pStatement = connection.prepareStatement(GET_ENTRIES))
		{
			pStatement.setString(1, cartCode);
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
	public Optional<CartEntry> findByProductCode(final String productCode, final String cartCode)
	{
		CartEntry cartEntry = null;
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement pStatement = connection.prepareStatement(GET_ENTRY_BY_PRODUCT_CODE))
		{
			pStatement.setString(1, productCode);
			pStatement.setString(2, cartCode);
			final ResultSet rSet = pStatement.executeQuery();
			if (rSet.next())
			{
				cartEntry = mapCartEntry(rSet);
			}
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
		return Optional.ofNullable(cartEntry);
	}

	@Override
	public int findCurrentEntryNumber(final String cartCode)
	{
		int result = 0;
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement pStatement = connection.prepareStatement(CURRENT_ENTRY_NUMBER))
		{
			pStatement.setString(1, cartCode);
			final ResultSet resultSet = pStatement.executeQuery();
			if (resultSet.next())
			{
				result = resultSet.getInt(ApplicationConstant.CURRENT_ENTRY_NUMBER);
			}
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CartEntry findEntry(final int entryId)
	{
		CartEntry cartEntry = null;
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement pStatement = connection.prepareStatement(GET_ENTRY))
		{
			pStatement.setInt(1, entryId);
			final ResultSet rSet = pStatement.executeQuery();
			if (rSet.next())
			{
				cartEntry = mapCartEntry(rSet);
			}
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
		return cartEntry;
	}

	@Override
	public boolean deleteEntry(final int id)
	{
		boolean res = false;
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			final PreparedStatement pStatement = connection.prepareStatement(DELETE_ENTRY);
			pStatement.setInt(1, id);
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

	private CartEntry mapCartEntry(final ResultSet rSet) throws SQLException
	{
		final CartEntry cartEntry = new CartEntry();
		cartEntry.setEntryNumber(rSet.getInt(ApplicationConstant.ENTRY_NUMBER));
		cartEntry.setQuantity(rSet.getInt(ApplicationConstant.PRODUCT_QUANTITY));
		cartEntry.setId(rSet.getInt(ApplicationConstant.ID));
		cartEntry.setTotalPrice(rSet.getDouble(ApplicationConstant.TOTAL_PRICE));
		final Product product = new Product();
		product.setId(rSet.getInt(ApplicationConstant.PRODUCT_ID));
		product.setPrice(rSet.getInt(ApplicationConstant.PRICE));
		product.setName(rSet.getString(ApplicationConstant.PRODUCT_NAME));
		product.setDescription(rSet.getString(ApplicationConstant.DESCRIPTION));
		product.setCode(rSet.getString(ApplicationConstant.PRODUCT_CODE_DB));
		cartEntry.setProduct(product);
		return cartEntry;
	}
}
