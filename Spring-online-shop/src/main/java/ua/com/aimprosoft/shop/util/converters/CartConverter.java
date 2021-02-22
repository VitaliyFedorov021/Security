package ua.com.aimprosoft.shop.util.converters;

import ua.com.aimprosoft.shop.entities.Cart;


public class CartConverter
{
	public static ua.com.aimprosoft.shop.dto.Cart entityToDto(final Cart cart)
	{
		final ua.com.aimprosoft.shop.dto.Cart dto = new ua.com.aimprosoft.shop.dto.Cart();
		dto.setCode(cart.getCode());
		dto.setDeliveryAddress(cart.getDeliveryAddress());
		dto.setTotalPrice(cart.getTotalPrice());
		dto.setPlacedDate(cart.getPlacedDate());
		dto.setCartEntries(cart.getCartEntries());
		return dto;
	}
}
