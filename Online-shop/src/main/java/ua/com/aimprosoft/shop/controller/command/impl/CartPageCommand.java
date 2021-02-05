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


public class CartPageCommand extends AbstractCommand
{
	private final CartService cartService;
	private final CartEntryService cartEntryService;
	private final SessionService sessionService;

	public CartPageCommand()
	{
		this.cartService = new CartServiceImpl();
		this.cartEntryService = new CartEntryServiceImpl();
		this.sessionService = new SessionServiceImpl();
	}

	@Override
	public void process() throws ServletException, IOException
	{
		final Customer customer = sessionService.getCurrentCustomer(request.getSession());
		final Cart cart = cartService.getActiveCart(customer);
		final List<CartEntry> cartEntries = cartEntryService.getEntries(cart.getCode());
		if (cartEntries.size() == 0)
		{
			forward(ApplicationConstant.EMPTY_CART);
			return;
		}
		cart.setCartEntries(cartEntries);
		request.setAttribute(ApplicationConstant.CART, cart);
		forward(ApplicationConstant.SHOW_CART);
	}
}
