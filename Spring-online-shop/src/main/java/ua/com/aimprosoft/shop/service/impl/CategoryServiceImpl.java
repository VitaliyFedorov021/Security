package ua.com.aimprosoft.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.aimprosoft.shop.dao.CategoryDao;
import ua.com.aimprosoft.shop.models.Category;
import ua.com.aimprosoft.shop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService
{
	private final CategoryDao categoryDao;

	@Autowired
	public CategoryServiceImpl(final CategoryDao categoryDao)
	{
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> getAll()
	{
		return categoryDao.findAll();
	}
}
