package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Address;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.SessionService;
import ua.com.aimprosoft.shop.service.impl.CartEntryServiceImpl;
import ua.com.aimprosoft.shop.service.impl.CartServiceImpl;
import ua.com.aimprosoft.shop.service.impl.SessionServiceImpl;
import ua.com.aimprosoft.shop.util.Extractor;
import ua.com.aimprosoft.shop.util.Validator;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.impl.AddressExtractor;
import ua.com.aimprosoft.shop.util.impl.AddressValidator;


public class SaveDeliveryAddressCommand extends AbstractCommand
{
	private final Extractor<Address> extractor;
	private final CartService cartService;
	private final SessionService sessionService;
	private final Validator<Address> addressValidator;

	public SaveDeliveryAddressCommand()
	{
		this.extractor = new AddressExtractor();
		this.cartService = new CartServiceImpl();
		this.sessionService = new SessionServiceImpl();
		this.addressValidator = new AddressValidator();
	}

	@Override
	public void process() throws ServletException, IOException
	{
		final Customer customer = sessionService.getCurrentCustomer(request.getSession());
		List<Exception> exceptions = new ArrayList<>();
		final Cart cart = cartService.getActiveCart(customer);
		final Address address = extractor.map(request);
		if (address.getStreet() == null) {
			response.sendRedirect(ApplicationConstant.CHECKOUT_COMMAND);
			return;
		}
		addressValidator.validate(address, exceptions);
		if (exceptions.size() > 0)
		{
			request.setAttribute(ApplicationConstant.MESSAGE, exceptions);
			forward(ApplicationConstant.CHECKOUT_PAGE);
			return;
		}
		cartService.placeOrder(cart, address);
		response.sendRedirect(String.format(ApplicationConstant.CONFIRMATION_PAGE_PATH, cart.getCode()));
	}
}
