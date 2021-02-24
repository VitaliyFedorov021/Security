package ua.com.aimprosoft.shop.util.converters;

import java.util.ArrayList;
import java.util.List;

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

	public static List<CategoryDto> castToDto(final List<Category> categories)
	{
		final List<CategoryDto> categoriesDto = new ArrayList<>();
		for (final Category c : categories)
		{
			categoriesDto.add(CategoryConverter.entityToDto(c));
		}
		return categoriesDto;
	}
}
