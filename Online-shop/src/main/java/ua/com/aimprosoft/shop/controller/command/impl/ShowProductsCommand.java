package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.service.ProductService;
import ua.com.aimprosoft.shop.service.impl.ProductServiceImpl;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class ShowProductsCommand extends AbstractCommand
{
	private final ProductService productService;

	public ShowProductsCommand()
	{
		this.productService = new ProductServiceImpl();
	}

	@Override
	public void process() throws ServletException, IOException
	{
		int categoryId = Integer.parseInt(request.getParameter(ApplicationConstant.C_ID));
		List<Product> products = productService.getAllByCategoryId(categoryId);
		request.setAttribute(ApplicationConstant.PRODUCTS, products);
		forward(ApplicationConstant.PLP_PATH);

	}
}
