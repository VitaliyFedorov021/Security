package ua.com.aimprosoft.shop.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.com.aimprosoft.shop.entities.Category;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class CategoryMapper implements RowMapper<Category>
{
	@Override
	public Category mapRow(final ResultSet resultSet, final int i) throws SQLException
	{
		final int id = resultSet.getInt(ApplicationConstant.ID);
		final Category category = new Category();
		category.setId(id);
		category.setCode(resultSet.getString(ApplicationConstant.CODE));
		category.setName(resultSet.getString(ApplicationConstant.NAME));
		category.setQuantityOfProducts(resultSet.getInt(ApplicationConstant.PRODUCT_QUANTITY_DB));
		return category;
	}
}
