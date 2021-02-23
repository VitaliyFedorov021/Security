package ua.com.aimprosoft.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.aimprosoft.shop.dao.ProductDao;
import ua.com.aimprosoft.shop.dto.ProductDto;
import ua.com.aimprosoft.shop.entities.Product;
import ua.com.aimprosoft.shop.service.ProductService;
import ua.com.aimprosoft.shop.util.converters.ProductConverter;


@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private final ProductDao productDao;

	@Autowired
	public ProductServiceImpl(final ProductDao productDao)
	{
		this.productDao = productDao;
	}

	@Override
	public List<ProductDto> getAllByCategoryCode(final String categoryCode)
	{
		return castToDto(productDao.findAllByCategoryCode(categoryCode));
	}

	@Override
	public Product getByProductCode(final String productCode)
	{
		return productDao.findByProductCode(productCode);
	}

	private List<ProductDto> castToDto(final List<Product> products)
	{
		final List<ProductDto> productsDto = new ArrayList<>();
		for (final Product p : products)
		{
			productsDto.add(ProductConverter.entityToDto(p));
		}
		return productsDto;
	}
}
