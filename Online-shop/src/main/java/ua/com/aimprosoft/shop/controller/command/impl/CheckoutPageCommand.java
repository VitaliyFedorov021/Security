package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.SessionService;
import ua.com.aimprosoft.shop.service.impl.CartEntryServiceImpl;
import ua.com.aimprosoft.shop.service.impl.CartServiceImpl;
import ua.com.aimprosoft.shop.service.impl.SessionServiceImpl;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class CheckoutPageCommand extends AbstractCommand
{
	private final SessionService sessionService;
	private final CartService cartService;
	private final CartEntryService cartEntryService;

	public CheckoutPageCommand()
	{
		this.sessionService = new SessionServiceImpl();
		this.cartService = new CartServiceImpl();
		this.cartEntryService = new CartEntryServiceImpl();
	}

	@Override
	public void process() throws ServletException, IOException
	{
		logging(request, response);
		final Customer customer = sessionService.getCurrentCustomer(request.getSession());
		if (customer == null) {
			String path = request.getRequestURI() + ApplicationConstant.QUESTION_MARK + request.getQueryString().trim();
			servletContext.setAttribute(ApplicationConstant.PATH, path);
			response.sendRedirect(ApplicationConstant.LOGIN_PAGE_PATH);
			return;
		}
		final Cart cart = cartService.getActiveCart(customer);
		final List<CartEntry> cartEntries = cartEntryService.getEntries(cart.getCode());
		if (cartEntries.size() == 0) {
			response.sendRedirect(ApplicationConstant.SHOW_CART_COMMAND);
			return;
		}
		cart.setCartEntries(cartEntries);
		request.setAttribute(ApplicationConstant.CART, cart);
		request.setAttribute(ApplicationConstant.CUSTOMER, customer);
		forward(ApplicationConstant.CHECKOUT_PAGE);
	}
}