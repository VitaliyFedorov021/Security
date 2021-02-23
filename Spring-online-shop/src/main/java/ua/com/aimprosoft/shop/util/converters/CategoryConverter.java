package ua.com.aimprosoft.shop.util.converters;

import ua.com.aimprosoft.shop.dto.CategoryDto;
import ua.com.aimprosoft.shop.entities.Category;


public class CategoryConverter
{
	public static CategoryDto entityToDto(final Category category)
	{
		final CategoryDto dto = new CategoryDto();
		dto.setCode(category.getCode());
		dto.setName(category.getName());
		dto.setQuantityOfProducts(category.getQuantityOfProducts());
		return dto;
	}
}
