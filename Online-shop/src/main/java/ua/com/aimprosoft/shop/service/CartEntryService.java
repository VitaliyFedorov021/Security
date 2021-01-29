package ua.com.aimprosoft.shop.service;

import java.util.List;

import ua.com.aimprosoft.shop.models.CartEntry;


public interface CartEntryService
{
	boolean addEntryToCart(CartEntry cartEntry);

	boolean updateEntry(CartEntry cartEntry);

	List<CartEntry> getEntriesByCartId(int cartId);

	boolean deleteEntry(CartEntry cartEntry);
}
