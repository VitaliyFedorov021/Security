package ua.com.aimprosoft.shop.dao;

import java.util.List;
import java.util.Optional;

import ua.com.aimprosoft.shop.models.CartEntry;


public interface CartEntryDao
{
	void insertEntry(CartEntry cartEntry);

	void updateEntry(CartEntry cartEntry);

	List<CartEntry> findEntriesByCartCode(String cartCode);

	Optional<CartEntry> findByProductCode(String productCode, String cartCode);

	int findCurrentEntryNumber(String cartCode);

	void deleteEntry(int id);
}
