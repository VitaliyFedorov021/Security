package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.impl.CartEntryServiceImpl;
import ua.com.aimprosoft.shop.service.impl.CartServiceImpl;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class OrderConfirmationPageCommand extends AbstractCommand
{
	private final CartService cartService;
	private final CartEntryService cartEntryService;

	public OrderConfirmationPageCommand()
	{
		this.cartService = new CartServiceImpl();
		this.cartEntryService = new CartEntryServiceImpl();
	}

	@Override
	public void process() throws ServletException, IOException
	{
		String cartCode = request.getParameter(ApplicationConstant.CODE);
		final Cart cart = cartService.getCartByCode(cartCode);
		if (cart == null) {
			response.sendRedirect(ApplicationConstant.HOME);
			return;
		}
		final List<CartEntry> entries = cartEntryService.getEntries(cart.getCode());
		if (entries.size() == 0) {
			response.sendRedirect(ApplicationConstant.SHOW_CART_COMMAND);
			return;
		}
		cart.setCartEntries(entries);
		request.setAttribute(ApplicationConstant.CART, cart);
		forward(ApplicationConstant.CONFIRMATION_PAGE);
	}
}
