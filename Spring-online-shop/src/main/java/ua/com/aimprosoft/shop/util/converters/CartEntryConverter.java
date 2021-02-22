package ua.com.aimprosoft.shop.util.converters;

import ua.com.aimprosoft.shop.entities.CartEntry;


public class CartEntryConverter
{

	public static ua.com.aimprosoft.shop.dto.CartEntry entityToDto(final CartEntry cartEntry)
	{
		final ua.com.aimprosoft.shop.dto.CartEntry dto = new ua.com.aimprosoft.shop.dto.CartEntry();
		dto.setProduct(cartEntry.getProduct());
		dto.setQuantity(cartEntry.getQuantity());
		dto.setTotalPrice(cartEntry.getTotalPrice());
		return dto;
	}
}
