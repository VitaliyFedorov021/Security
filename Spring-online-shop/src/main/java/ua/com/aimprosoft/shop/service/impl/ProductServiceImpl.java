package ua.com.aimprosoft.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.aimprosoft.shop.dao.ProductDao;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService
{
	private final ProductDao productDao;

	@Autowired
	public ProductServiceImpl(final ProductDao productDao)
	{
		this.productDao = productDao;
	}

	@Override
	public List<Product> getAllByCategoryCode(String categoryCode)
	{
		return productDao.findAllByCategoryCode(categoryCode);
	}

	@Override
	public Product getByProductCode(final String productCode)
	{
		return productDao.findByProductCode(productCode);
	}
}
