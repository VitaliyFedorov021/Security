package ua.com.aimprosoft.shop.service.impl;

import java.util.List;

import ua.com.aimprosoft.shop.dao.CartEntryDao;
import ua.com.aimprosoft.shop.dao.impl.CartEntryDaoImpl;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.service.CartEntryService;


public class CartEntryServiceImpl implements CartEntryService
{
	private final CartEntryDao cartEntryDao;

	public CartEntryServiceImpl()
	{
		this.cartEntryDao = new CartEntryDaoImpl();
	}

	@Override
	public boolean addEntryToCart(final CartEntry cartEntry)
	{
		return cartEntryDao.insertEntry(cartEntry);
	}

	@Override
	public boolean updateEntry(final CartEntry cartEntry)
	{
		return cartEntryDao.updateEntry(cartEntry);
	}

	@Override
	public List<CartEntry> getEntriesByCartId(final int cartId)
	{
		return cartEntryDao.findEntriesByCartId(cartId);
	}

	@Override
	public boolean deleteEntry(final CartEntry cartEntry)
	{
		return cartEntryDao.deleteEntry(cartEntry);
	}
}
