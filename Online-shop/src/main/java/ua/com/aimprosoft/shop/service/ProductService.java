package ua.com.aimprosoft.shop.service;

import java.util.List;

import ua.com.aimprosoft.shop.models.Product;


public interface ProductService
{
	List<Product> getAllByCategoryId(int categoryId);

	Product findByCode(String code);
}
