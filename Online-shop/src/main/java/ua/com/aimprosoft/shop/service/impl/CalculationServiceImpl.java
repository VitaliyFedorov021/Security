package ua.com.aimprosoft.shop.service.impl;

import java.util.List;

import ua.com.aimprosoft.shop.dao.CartDao;
import ua.com.aimprosoft.shop.dao.CartEntryDao;
import ua.com.aimprosoft.shop.dao.impl.CartDaoImpl;
import ua.com.aimprosoft.shop.dao.impl.CartEntryDaoImpl;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.service.CalculationService;


public class CalculationServiceImpl implements CalculationService
{
	private final CartDao cartDao;
	private final CartEntryDao cartEntryDao;

	public CalculationServiceImpl()
	{
		this.cartDao = new CartDaoImpl();
		this.cartEntryDao = new CartEntryDaoImpl();
	}

	@Override
	public void calculation(final Cart cart)
	{
		final String cartCode = cart.getCode();
		final List<CartEntry> entries = cartEntryDao.findEntriesByCartCode(cartCode);
		double newCartPrice = 0.0;
		for (final CartEntry cartEntry : entries)
		{
			final double newEntryPrice = cartEntry.getProduct().getPrice() * cartEntry.getQuantity();
			cartEntry.setTotalPrice(newEntryPrice);
			cartEntryDao.updateEntry(cartEntry);
			newCartPrice += newEntryPrice;
		}
		cart.setTotalPrice(newCartPrice);
		cartDao.updateCart(cart);
	}
}
