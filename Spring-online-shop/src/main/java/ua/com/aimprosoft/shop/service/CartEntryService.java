package ua.com.aimprosoft.shop.service;

import java.util.List;

import ua.com.aimprosoft.shop.dto.CartDto;
import ua.com.aimprosoft.shop.entities.CartEntry;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;


public interface CartEntryService
{
	void addEntry(final String code, final CartDto cartDto, final int quantity);

	List<CartEntry> getEntriesByCartCode(String cartCode);

	void updateEntryQuantity(String code, int quantity, CartDto cartDto) throws IncorrectOperationException;

	void deleteEntry(CartDto cartDto, String productCode) throws IncorrectOperationException;
}
