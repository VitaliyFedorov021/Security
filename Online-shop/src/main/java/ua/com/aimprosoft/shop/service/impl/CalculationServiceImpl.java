package ua.com.aimprosoft.shop.service.impl;

import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.service.CalculationService;


public class CalculationServiceImpl implements CalculationService
{
	@Override
	public void calculation(final Cart cart, final CartEntry cartEntry)
	{
		final double oldPrice = cartEntry.getTotalPrice();
		final double newPrice = cartEntry.getProduct().getPrice() * cartEntry.getQuantity();
		cartEntry.setTotalPrice(newPrice);
		cart.setTotalPrice(cart.getTotalPrice() - oldPrice + newPrice);
	}
}
