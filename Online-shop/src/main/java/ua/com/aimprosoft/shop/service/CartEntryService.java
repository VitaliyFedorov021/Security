package ua.com.aimprosoft.shop.service;

import java.util.List;

import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;


public interface CartEntryService
{
	void addEntry(final String code, final Cart cart, final int quantity);

	List<CartEntry> getEntries(String code);

	void deleteEntry(String productCode, Cart cart);

	void updateEntryQuantity(String code, int quantity, Cart cart);
}
