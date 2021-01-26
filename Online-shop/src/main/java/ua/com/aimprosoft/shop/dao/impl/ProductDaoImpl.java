package ua.com.aimprosoft.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.com.aimprosoft.shop.dao.ProductDao;
import ua.com.aimprosoft.shop.database.DataSource;
import ua.com.aimprosoft.shop.database.impl.HikariDataSourceImpl;
import ua.com.aimprosoft.shop.models.Category;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class ProductDaoImpl implements ProductDao
{
	private final DataSource dataSource;

	private static final String PRODUCTS_BY_CATEGORY = "SELECT p.id, p.name, p.code, p.price, p.description,"
			+ "c.code as c_code, c.id as c_id, c.name as c_name FROM product p "
			+ "JOIN category c on p.category_id = c.id WHERE c.id = ?";

	public ProductDaoImpl()
	{
		this.dataSource = new HikariDataSourceImpl();
	}

	@Override
	public List<Product> findAllByCategoryId(int categoryId)
	{
		List<Product> products = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pStatement = connection.prepareStatement(PRODUCTS_BY_CATEGORY);)
		{
			pStatement.setInt(1, categoryId);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next())
			{
				products.add(mapProduct(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return products;
	}

	private Product mapProduct(ResultSet resultSet) throws SQLException
	{
		Product product = new Product();
		product.setId(resultSet.getInt(ApplicationConstant.ID));
		product.setPrice(resultSet.getInt(ApplicationConstant.PRICE));
		product.setName(resultSet.getString(ApplicationConstant.NAME));
		product.setDescription(resultSet.getString(ApplicationConstant.DESCRIPTION));
		product.setCode(resultSet.getString(ApplicationConstant.CODE));
		Category category = new Category();
		category.setId(resultSet.getInt(ApplicationConstant.CATEGORY_ID));
		category.setName(resultSet.getString(ApplicationConstant.CATEGORY_NAME));
		category.setCode(resultSet.getString(ApplicationConstant.CATEGORY_CODE));
		product.setCategory(category);
		return product;
	}
}
