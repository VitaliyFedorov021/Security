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

	private static final String ALL_CATEGORIES = "SELECT * FROM category";
	private static final String PRODUCTS_QUANTITY = "SELECT COUNT(*) as q FROM product p WHERE category_id = ?";

	public CategoryDaoImpl()
	{
		this.dataSource = new HikariDataSourceImpl();
	}

	@Override
	public List<Category> findAll()
	{
		List<Category> categories = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pStatement = connection.prepareStatement(ALL_CATEGORIES);)
		{

			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next())
			{
				categories.add(mapCategory(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return categories;
	}

	private int findProductsQuantity(final int categoryId)
	{
		int result = 0;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pStatement = connection.prepareStatement(PRODUCTS_QUANTITY);)
		{
			pStatement.setInt(1, categoryId);
			ResultSet rSet = pStatement.executeQuery();
			rSet.next();
			result = rSet.getInt("q");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	private Category mapCategory(ResultSet rSet) throws SQLException
	{
		int id = rSet.getInt(ApplicationConstant.ID);
		Category category = new Category();
		category.setId(id);
		category.setCode(rSet.getString(ApplicationConstant.CODE));
		category.setName(rSet.getString(ApplicationConstant.NAME));
		category.setQuantityOfProducts(findProductsQuantity(id));
		return category;
	}
}
