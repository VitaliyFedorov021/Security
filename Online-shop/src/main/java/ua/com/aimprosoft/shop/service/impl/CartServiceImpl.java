package ua.com.aimprosoft.shop.service.impl;

import java.util.Optional;
import java.util.Random;

import ua.com.aimprosoft.shop.dao.CartDao;
import ua.com.aimprosoft.shop.dao.impl.CartDaoImpl;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;


public class CartServiceImpl implements CartService
{
	private final CartDao cartDao;
	private final CartEntryService cartEntryService;

	public CartServiceImpl()
	{
		this.cartDao = new CartDaoImpl();
		this.cartEntryService = new CartEntryServiceImpl();
	}

	@Override
	public Cart getCartByEntryId(final int entryId)
	{
		return cartDao.findCartByEntryId(entryId);
	}

	@Override
	public void addProductToCart(final Customer customer, final int quantity, final String code)
	{
		final Cart cart = getActiveCart(customer);
		cartEntryService.addEntry(code, cart, quantity);
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
		cartEntryService.deleteEntry(productCode, cart);
	}

	@Override
	public void updateProductQuantity(final Customer customer, final int quantity, final String code)
			throws IncorrectOperationException
	{
		final Cart cart = getActiveCart(customer);
		cartEntryService.updateEntryQuantity(code, quantity, cart);
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
