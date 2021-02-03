package ua.com.aimprosoft.shop.service.impl;

import java.util.Optional;
import java.util.Random;

import ua.com.aimprosoft.shop.dao.CartDao;
import ua.com.aimprosoft.shop.dao.impl.CartDaoImpl;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CartService;


public class CartServiceImpl implements CartService
{
	private final CartDao cartDao;

	public CartServiceImpl()
	{
		this.cartDao = new CartDaoImpl();
	}

	@Override
	public boolean updateCart(final Cart cart)
	{
		return cartDao.updateCart(cart);
	}

	@Override
	public Optional<Cart> getCart(final Customer customer)
	{
		final Optional<Cart> cartOptional = cartDao.findActiveCart(customer.getEmail());
		return cartOptional;
	}

	@Override
	public Cart saveCart(final Customer customer)
	{
		final Cart cart = new Cart();
		cart.setCode(generateCode());
		customer.setCart(cart);
		cart.setCustomer(customer);
		cartDao.insertCart(cart);
		return cart;
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
