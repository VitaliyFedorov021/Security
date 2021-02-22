package ua.com.aimprosoft.shop.service;

import java.util.List;

import ua.com.aimprosoft.shop.entities.Product;


public interface ProductService
{
	List<Product> getAllByCategoryCode(String categoryCode);

	Product getByProductCode(String productCode);
}
