package ua.com.aimprosoft.shop.dao;

import java.util.List;

import ua.com.aimprosoft.shop.models.Category;


public interface CategoryDao
{
	List<Category> findAll();
}
