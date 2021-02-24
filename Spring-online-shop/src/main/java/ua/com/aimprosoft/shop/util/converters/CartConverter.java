package ua.com.aimprosoft.shop.util.converters;

import java.util.ArrayList;
import java.util.List;

import ua.com.aimprosoft.shop.dto.CartDto;
import ua.com.aimprosoft.shop.dto.CartEntryDto;
import ua.com.aimprosoft.shop.entities.Cart;
import ua.com.aimprosoft.shop.entities.CartEntry;


public class CartConverter
{
	public static CartDto entityToDto(final Cart cart)
	{
		final CartDto dto = new CartDto();
		dto.setId(cart.getId());
		dto.setCode(cart.getCode());
		dto.setDeliveryAddress(cart.getDeliveryAddress());
		dto.setTotalPrice(cart.getTotalPrice());
		dto.setPlacedDate(cart.getPlacedDate());
		final List<CartEntry> entries = cart.getCartEntries();
		if (entries == null)
		{
			dto.setCartEntries(new ArrayList<>());
			return dto;
		}
		dto.setCartEntries(CartEntryConverter.castToDto(entries));
		return dto;
	}

	public static Cart dtoToEntity(final CartDto cartDto)
	{
		final Cart cart = new Cart();
		cart.setId(cartDto.getId());
		cart.setTotalPrice(cartDto.getTotalPrice());
		cart.setPlacedDate(cartDto.getPlacedDate());
		cart.setDeliveryAddress(cartDto.getDeliveryAddress());
		cart.setCode(cartDto.getCode());
		final List<CartEntryDto> entries = cartDto.getCartEntries();
		if (entries == null)
		{
			cart.setCartEntries(null);
			return cart;
		}
		cart.setCartEntries(CartEntryConverter.castToEntry(entries));
		return cart;
	}
}
