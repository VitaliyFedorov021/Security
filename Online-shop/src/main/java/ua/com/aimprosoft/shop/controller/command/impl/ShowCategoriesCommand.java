package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Category;
import ua.com.aimprosoft.shop.service.CategoryService;
import ua.com.aimprosoft.shop.service.impl.CategoryServiceImpl;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class ShowCategoriesCommand extends AbstractCommand
{
	private final CategoryService categoryService;

	public ShowCategoriesCommand()
	{
		this.categoryService = new CategoryServiceImpl();
	}

	@Override
	public void process() throws ServletException, IOException
	{
		List<Category> categories = categoryService.getAll();
		request.setAttribute(ApplicationConstant.CATEGORIES, categories);
		forward(ApplicationConstant.CATEGORIES_PAGE);
	}
}
