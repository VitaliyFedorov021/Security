package ua.com.aimprosoft.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.aimprosoft.shop.dao.CartDao;
import ua.com.aimprosoft.shop.dao.CartEntryDao;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.service.CalculationService;


@Component
public class CalculationServiceImpl implements CalculationService
{
	private final CartDao cartDao;
	private final CartEntryDao cartEntryDao;

	@Autowired
	public CalculationServiceImpl(final CartDao cartDao, final CartEntryDao cartEntryDao)
	{
		this.cartDao = cartDao;
		this.cartEntryDao = cartEntryDao;
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
