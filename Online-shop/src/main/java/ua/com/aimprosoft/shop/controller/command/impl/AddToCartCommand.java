package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.SessionService;
import ua.com.aimprosoft.shop.service.impl.CartEntryServiceImpl;
import ua.com.aimprosoft.shop.service.impl.SessionServiceImpl;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class AddToCartCommand extends AbstractCommand
{
	private final CartEntryService cartEntryService;
	private final SessionService sessionService;

	public AddToCartCommand()
	{
		this.cartEntryService = new CartEntryServiceImpl();
		this.sessionService = new SessionServiceImpl();
	}

	@Override
	public void process() throws ServletException, IOException
	{
		final int quantity = Integer.parseInt(request.getParameter(ApplicationConstant.QUANTITY));
		final String code = request.getParameter(ApplicationConstant.PRODUCT_CODE);
		final Customer customer = sessionService.getCurrentCustomer(request.getSession());
		cartEntryService.addEntryToCart(customer, quantity, code);
		response.setContentType(ApplicationConstant.TYPE_TEXT);
		response.getWriter().write(ApplicationConstant.SUCCESS_TEXT);
	}
}

