package ua.com.aimprosoft.shop.service.impl;

import java.util.List;
import java.util.Optional;

import ua.com.aimprosoft.shop.dao.CategoryDao;
import ua.com.aimprosoft.shop.dao.impl.CategoryDaoImpl;
import ua.com.aimprosoft.shop.models.Category;
import ua.com.aimprosoft.shop.service.CategoryService;


public class CategoryServiceImpl implements CategoryService
{
	private CategoryDao categoryDao;

	public CategoryServiceImpl()
	{
		this.categoryDao = new CategoryDaoImpl();
	}

	@Override
	public List<Category> getAll()
	{
		return categoryDao.findAll();
	}
}
