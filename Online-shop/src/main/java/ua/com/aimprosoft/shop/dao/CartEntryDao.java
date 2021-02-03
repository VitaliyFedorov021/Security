package ua.com.aimprosoft.shop.dao;

import java.util.List;
import java.util.Optional;

import ua.com.aimprosoft.shop.models.CartEntry;


public interface CartEntryDao
{
	boolean insertEntry(CartEntry cartEntry);

	boolean updateEntry(CartEntry cartEntry);

	List<CartEntry> findEntriesByCartCode(String cartCode);

	Optional<CartEntry> findByProductCode(String productCode);

	int findCurrentEntryNumber(String cartCode);

	boolean deleteEntry(int id);
}
