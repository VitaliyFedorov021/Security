package ua.com.aimprosoft.shop.dao;

import java.util.List;

import ua.com.aimprosoft.shop.models.Product;


public interface ProductDao
{
	List<Product> findAllByCategoryCode(String categoryCode);

	Product findByProductCode(String productCode);
}
