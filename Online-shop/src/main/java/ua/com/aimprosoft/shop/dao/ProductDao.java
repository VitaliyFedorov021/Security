package ua.com.aimprosoft.shop.dao;

import java.util.List;
import java.util.Optional;

import ua.com.aimprosoft.shop.models.Product;


public interface ProductDao
{
	List<Product> findAllByCategoryId(int categoryId);
}
