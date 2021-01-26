package ua.com.aimprosoft.shop.dao;

import java.util.List;

import ua.com.aimprosoft.shop.models.Product;


public interface ProductDao
{
	List<Product> findAllByCategoryId(int categoryId);

	Product findByCode(String code);
}
