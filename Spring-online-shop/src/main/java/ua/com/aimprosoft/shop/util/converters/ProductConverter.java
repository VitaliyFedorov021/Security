package ua.com.aimprosoft.shop.util.converters;

import java.util.ArrayList;
import java.util.List;

import ua.com.aimprosoft.shop.dto.ProductDto;
import ua.com.aimprosoft.shop.entities.Product;


public class ProductConverter
{
	public static ProductDto entityToDto(final Product product)
	{
		final ProductDto dto = new ProductDto();
		dto.setCategory(product.getCategory());
		dto.setCode(product.getCode());
		dto.setPrice(product.getPrice());
		dto.setDescription(product.getDescription());
		dto.setName(product.getName());
		return dto;
	}

	public static List<ProductDto> castToDto(final List<Product> products)
	{
		final List<ProductDto> productsDto = new ArrayList<>();
		for (final Product p : products)
		{
			productsDto.add(ProductConverter.entityToDto(p));
		}
		return productsDto;
	}
}
