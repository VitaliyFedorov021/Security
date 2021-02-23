package ua.com.aimprosoft.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.aimprosoft.shop.dao.CategoryDao;
import ua.com.aimprosoft.shop.dto.CategoryDto;
import ua.com.aimprosoft.shop.entities.Category;
import ua.com.aimprosoft.shop.service.CategoryService;
import ua.com.aimprosoft.shop.util.converters.CategoryConverter;


@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private final CategoryDao categoryDao;

	public CategoryServiceImpl(final CategoryDao categoryDao)
	{
		this.categoryDao = categoryDao;
	}

	@Override
	public List<CategoryDto> getAll()
	{
		return castToDto(categoryDao.findAll());
	}

	private List<CategoryDto> castToDto(final List<Category> categories)
	{
		final List<CategoryDto> categoriesDto = new ArrayList<>();
		for (final Category c : categories)
		{
			categoriesDto.add(CategoryConverter.entityToDto(c));
		}
		return categoriesDto;
	}
}
