package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.service.ProductService;
import ua.com.aimprosoft.shop.service.impl.ProductServiceImpl;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class ProductCommand extends AbstractCommand
{
	private final ProductService productService;

	public ProductCommand()
	{
		this.productService = new ProductServiceImpl();
	}

	@Override
	public void process() throws ServletException, IOException
	{
		logging(request, response);
		final String code = request.getParameter(ApplicationConstant.CODE);
		final Product product = productService.findByCode(code);
		request.setAttribute(ApplicationConstant.PRODUCT, product);
		forward(ApplicationConstant.PDP_PATH);
	}
}
