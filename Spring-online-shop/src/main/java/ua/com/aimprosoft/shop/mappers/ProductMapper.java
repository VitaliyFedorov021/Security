package ua.com.aimprosoft.shop.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.com.aimprosoft.shop.models.Category;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class ProductMapper implements RowMapper<Product>
{
	@Override
	public Product mapRow(final ResultSet resultSet, final int i) throws SQLException
	{
		final Product product = new Product();
		product.setId(resultSet.getInt(ApplicationConstant.ID));
		product.setPrice(resultSet.getInt(ApplicationConstant.PRICE));
		product.setName(resultSet.getString(ApplicationConstant.NAME));
		product.setDescription(resultSet.getString(ApplicationConstant.DESCRIPTION));
		product.setCode(resultSet.getString(ApplicationConstant.CODE));
		return product;
	}
}
