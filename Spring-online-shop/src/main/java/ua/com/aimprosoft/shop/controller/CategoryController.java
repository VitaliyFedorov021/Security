package ua.com.aimprosoft.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.aimprosoft.shop.dto.CategoryDto;
import ua.com.aimprosoft.shop.service.CategoryService;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


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
		final List<CategoryDto> categoriesDto = categoryService.getAll();
		model.addAttribute(ApplicationConstant.CATEGORIES, categoriesDto);
		return "categoriesPage";
	}
}
