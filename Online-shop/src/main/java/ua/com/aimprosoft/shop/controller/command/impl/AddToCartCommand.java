package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.SessionService;
import ua.com.aimprosoft.shop.service.impl.CartServiceImpl;
import ua.com.aimprosoft.shop.service.impl.SessionServiceImpl;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class AddToCartCommand extends AbstractCommand
{
	private final CartService cartService;
	private final SessionService sessionService;

	public AddToCartCommand()
	{
		this.cartService = new CartServiceImpl();
		this.sessionService = new SessionServiceImpl();
	}

	@Override
	public void process() throws ServletException, IOException
	{
		logging(request, response);
		final int quantity = Integer.parseInt(request.getParameter(ApplicationConstant.QUANTITY));
		final String code = request.getParameter(ApplicationConstant.PRODUCT_CODE);
		final Customer customer = sessionService.getCurrentCustomer(request.getSession());
		cartService.addProductToCart(customer, quantity, code);
		response.setContentType(ApplicationConstant.TYPE_TEXT);
		response.getWriter().write(ApplicationConstant.SUCCESS_TEXT);
	}
}

