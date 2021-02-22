package ua.com.aimprosoft.shop.util.converters;

import ua.com.aimprosoft.shop.entities.Category;


public class CategoryConverter
{
	public static ua.com.aimprosoft.shop.dto.Category entityToDto(final Category category)
	{
		final ua.com.aimprosoft.shop.dto.Category dto = new ua.com.aimprosoft.shop.dto.Category();
		dto.setCode(category.getCode());
		dto.setName(category.getName());
		dto.setQuantityOfProducts(category.getQuantityOfProducts());
		return dto;
	}
}
