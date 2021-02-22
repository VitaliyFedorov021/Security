package ua.com.aimprosoft.shop.service;

import java.util.List;

import ua.com.aimprosoft.shop.entities.Cart;
import ua.com.aimprosoft.shop.entities.CartEntry;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;


public interface CartEntryService
{
	void addEntry(final String code, final Cart cart, final int quantity);

	List<CartEntry> getEntriesByCartCode(String cartCode);

	void updateEntryQuantity(String code, int quantity, Cart cart) throws IncorrectOperationException;

	void deleteEntry(Cart cart, String productCode) throws IncorrectOperationException;
}
