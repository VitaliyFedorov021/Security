package ua.com.aimprosoft.shop.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.com.aimprosoft.shop.dao.ProductDao;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.mappers.ProductMapper;


@Component
public class ProductDaoImpl implements ProductDao
{
	private final JdbcTemplate jdbcTemplate;
	private static final String PRODUCTS_BY_CATEGORY = "SELECT p.id, p.name, p.code, p.price, p.description,"
			+ "category.code FROM product p "
			+ "JOIN category on p.category_id = category.id WHERE category.code = ?";
	private static final String PRODUCT_BY_CODE = "SELECT p.id, p.name, p.code, p.price, p.description"
			+ " FROM product p "
			+ "WHERE p.code = ?";

	@Autowired
	public ProductDaoImpl(final JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Product> findAllByCategoryCode(final String categoryCode)
	{
		return jdbcTemplate.query(PRODUCTS_BY_CATEGORY, new Object[]{categoryCode}, new ProductMapper());
	}

	@Override
	public Product findByProductCode(final String productCode)
	{
		return jdbcTemplate.queryForObject(PRODUCT_BY_CODE, new ProductMapper(), productCode);
	}
}
