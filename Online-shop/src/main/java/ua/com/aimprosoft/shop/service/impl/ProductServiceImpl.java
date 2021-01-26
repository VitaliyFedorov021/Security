package ua.com.aimprosoft.shop.service.impl;

import java.util.List;

import ua.com.aimprosoft.shop.dao.ProductDao;
import ua.com.aimprosoft.shop.dao.impl.ProductDaoImpl;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.service.ProductService;


public class ProductServiceImpl implements ProductService
{
	private ProductDao productDao;

	public ProductServiceImpl()
	{
		this.productDao = new ProductDaoImpl();
	}

	@Override
	public List<Product> getAllByCategoryId(final int categoryId)
	{
		return productDao.findAllByCategoryId(categoryId);
	}
}
