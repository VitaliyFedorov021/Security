package ua.com.aimprosoft.shop.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.com.aimprosoft.shop.dao.CategoryDao;
import ua.com.aimprosoft.shop.models.Category;
import ua.com.aimprosoft.shop.mappers.CategoryMapper;

@Component
public class CategoryDaoImpl implements CategoryDao
{
	private final JdbcTemplate jdbcTemplate;
	private static final String ALL_CATEGORIES = "SELECT c.*, COUNT(p.category_id) as product_quantity "
			+ "FROM category c "
			+ "LEFT JOIN product p ON c.id = p.category_id "
			+ "GROUP BY c.id";

	@Autowired
	public CategoryDaoImpl(final JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Category> findAll()
	{
		return jdbcTemplate.query(ALL_CATEGORIES, new CategoryMapper());
	}
}
