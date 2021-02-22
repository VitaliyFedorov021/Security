package ua.com.aimprosoft.shop.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.aimprosoft.shop.dao.AddressDao;
import ua.com.aimprosoft.shop.dao.CartDao;
import ua.com.aimprosoft.shop.entities.Address;
import ua.com.aimprosoft.shop.entities.Cart;
import ua.com.aimprosoft.shop.entities.Customer;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;


@Service
public class CartServiceImpl implements CartService
{
	@Autowired
	private final CartDao cartDao;
	@Autowired
	private final CartEntryService cartEntryService;
	@Autowired
	private final AddressDao addressDao;

	public CartServiceImpl(final CartDao cartDao, final CartEntryService cartEntryService,
			final AddressDao addressDao)
	{
		this.cartDao = cartDao;
		this.cartEntryService = cartEntryService;
		this.addressDao = addressDao;
	}

	@Override
	public void addProductToCart(final Customer customer, final String productCode, final int quantity)
	{
		final Cart cart = getActiveCart(customer);
		cartEntryService.addEntry(productCode, cart, quantity);
	}

	@Override
	public Cart getActiveCart(final Customer customer)
	{
		final Optional<Cart> cartOptional = cartDao.findActiveCart(customer.getEmail());
		if (!cartOptional.isPresent())
		{
			final Cart cart = new Cart();
			cart.setCode(generateCode());
			cart.setCustomer(customer);
			cartDao.insertCart(cart);
			return cart;
		}
		return cartOptional.get();
	}

	@Override
	public void deleteProductFromCart(final Customer customer, final String productCode)
			throws IncorrectOperationException
	{
		final Cart cart = getActiveCart(customer);
		cartEntryService.deleteEntry(cart, productCode);
	}

	@Override
	public void updateProductQuantity(final Customer customer, final int quantity, final String code)
			throws IncorrectOperationException
	{
		final Cart cart = getActiveCart(customer);
		cartEntryService.updateEntryQuantity(code, quantity, cart);
	}

	@Override
	public void placeOrder(final Cart cart, final Address address)
	{
		addressDao.insertAddress(address);
		cart.setDeliveryAddress(address);
		cart.setPlacedDate(new Date());
		cartDao.updateCart(cart);
	}

	@Override
	public Optional<Cart> getCartByCode(final String cartCode)
	{
		return cartDao.findCartByCode(cartCode);
	}

	private String generateCode()
	{
		final int leftLimit = 97;
		final int rightLimit = 122;
		final int targetStringLength = 10;
		final Random random = new Random();
		final StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++)
		{
			final int randomLimitedInt = leftLimit + (int)
					(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		return buffer.toString();
	}
}
