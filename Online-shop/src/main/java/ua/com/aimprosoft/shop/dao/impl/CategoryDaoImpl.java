package ua.com.aimprosoft.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.com.aimprosoft.shop.dao.CategoryDao;
import ua.com.aimprosoft.shop.database.DataSource;
import ua.com.aimprosoft.shop.database.impl.HikariDataSourceImpl;
import ua.com.aimprosoft.shop.models.Category;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class CategoryDaoImpl implements CategoryDao
{
	private final DataSource dataSource;

	private static final String ALL_CATEGORIES = "SELECT c.*, COUNT(p.category_id) as product_quantity "
			+ "FROM category c "
			+ "LEFT JOIN product p ON c.id = p.category_id "
			+ "GROUP BY c.id";

	public CategoryDaoImpl()
	{
		this.dataSource = HikariDataSourceImpl.getInstance();
	}

	@Override
	public List<Category> findAll()
	{
		final List<Category> categories = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement pStatement = connection.prepareStatement(ALL_CATEGORIES);)
		{

			final ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next())
			{
				categories.add(mapCategory(resultSet));
			}
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
		return categories;
	}

	private Category mapCategory(final ResultSet rSet) throws SQLException
	{
		final int id = rSet.getInt(ApplicationConstant.ID);
		final Category category = new Category();
		category.setId(id);
		category.setCode(rSet.getString(ApplicationConstant.CODE));
		category.setName(rSet.getString(ApplicationConstant.NAME));
		category.setQuantityOfProducts(rSet.getInt(ApplicationConstant.PRODUCT_QUANTITY));
		return category;
	}
}
