package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.SessionService;
import ua.com.aimprosoft.shop.service.impl.CartServiceImpl;
import ua.com.aimprosoft.shop.service.impl.SessionServiceImpl;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class DeleteProductCommand extends AbstractCommand
{
	private final CartService cartService;
	private final SessionService sessionService;

	public DeleteProductCommand()
	{
		this.cartService = new CartServiceImpl();
		this.sessionService = new SessionServiceImpl();
	}

	@Override
	public void process() throws ServletException, IOException
	{
		try
		{
			final String productCode = request.getParameter(ApplicationConstant.PRODUCT_CODE);
			final Customer customer = sessionService.getCurrentCustomer(request.getSession());
			cartService.deleteProductFromCart(customer, productCode);
			response.sendRedirect(ApplicationConstant.SHOW_CART_COMMAND);
		}
		catch (final IncorrectOperationException e)
		{
			response.sendRedirect(ApplicationConstant.SHOW_CART_COMMAND);
		}
	}
}
