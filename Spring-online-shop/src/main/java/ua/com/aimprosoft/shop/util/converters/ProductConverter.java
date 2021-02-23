package ua.com.aimprosoft.shop.util.converters;

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
}
