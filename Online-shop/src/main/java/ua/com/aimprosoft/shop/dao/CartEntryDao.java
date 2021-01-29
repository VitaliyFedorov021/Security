package ua.com.aimprosoft.shop.dao;

import java.util.List;

import ua.com.aimprosoft.shop.models.CartEntry;


public interface CartEntryDao
{
	boolean insertEntry(CartEntry cartEntry);

	boolean updateEntry(CartEntry cartEntry);

	List<CartEntry> findEntriesByCartId(int cartId);

	boolean deleteEntry(CartEntry cartEntry);
}
