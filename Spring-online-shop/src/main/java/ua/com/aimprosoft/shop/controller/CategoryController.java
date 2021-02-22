package ua.com.aimprosoft.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.aimprosoft.shop.entities.Category;
import ua.com.aimprosoft.shop.service.CategoryService;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.converters.CategoryConverter;


@Controller
public class CategoryController
{
	@Autowired
	private final CategoryService categoryService;

	public CategoryController(final CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}

	@GetMapping("/categories")
	public String categoriesPage(final Model model)
	{
		final List<Category> categories = categoryService.getAll();
		final List<ua.com.aimprosoft.shop.dto.Category> categoriesDto = castToDto(categories);
		model.addAttribute(ApplicationConstant.CATEGORIES, categoriesDto);
		return "categoriesPage";
	}

	private List<ua.com.aimprosoft.shop.dto.Category> castToDto(final List<Category> categories)
	{
		final List<ua.com.aimprosoft.shop.dto.Category> categoriesDto = new ArrayList<>();
		for (final Category c : categories)
		{
			categoriesDto.add(CategoryConverter.entityToDto(c));
		}
		return categoriesDto;
	}
}
