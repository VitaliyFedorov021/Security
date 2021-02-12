package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Address;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.SessionService;
import ua.com.aimprosoft.shop.service.impl.CartEntryServiceImpl;
import ua.com.aimprosoft.shop.service.impl.CartServiceImpl;
import ua.com.aimprosoft.shop.service.impl.SessionServiceImpl;
import ua.com.aimprosoft.shop.util.Extractor;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.impl.AddressExtractor;


public class SaveDeliveryAddressCommand extends AbstractCommand
{
	private final Extractor<Address> extractor;
	private final CartService cartService;
	private final SessionService sessionService;
	private final CartEntryService cartEntryService;

	public SaveDeliveryAddressCommand()
	{
		this.extractor = new AddressExtractor();
		this.cartService = new CartServiceImpl();
		this.sessionService = new SessionServiceImpl();
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
		final Address address = extractor.map(request);
		if (address.getStreet() == null) {
			response.sendRedirect(ApplicationConstant.CHECKOUT_COMMAND);
			return;
		}
		cartService.placeOrder(cart, address);
		response.sendRedirect(String.format(ApplicationConstant.CONFIRMATION_PATH, cart.getCode()));
	}
}
