package ua.com.aimprosoft.shop.util.converters;

import ua.com.aimprosoft.shop.entities.Product;


public class ProductConverter
{
	public static ua.com.aimprosoft.shop.dto.Product entityToDto(final Product product)
	{
		final ua.com.aimprosoft.shop.dto.Product dto = new ua.com.aimprosoft.shop.dto.Product();
		dto.setCategory(product.getCategory());
		dto.setCode(product.getCode());
		dto.setPrice(product.getPrice());
		dto.setDescription(product.getDescription());
		dto.setName(product.getName());
		return dto;
	}
}
