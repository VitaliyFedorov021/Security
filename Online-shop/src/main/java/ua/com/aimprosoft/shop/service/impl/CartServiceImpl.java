package ua.com.aimprosoft.shop.service.impl;

import java.util.List;

import ua.com.aimprosoft.shop.dao.CartDao;
import ua.com.aimprosoft.shop.dao.impl.CartDaoImpl;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.service.CartService;


public class CartServiceImpl implements CartService
{
	private final CartDao cartDao;

	public CartServiceImpl()
	{
		this.cartDao = new CartDaoImpl();
	}

	@Override
	public boolean saveCart(final Cart cart)
	{
		return cartDao.insertCart(cart);
	}

	@Override
	public List<Cart> getCartByCustomerId(final int customerId)
	{
		return cartDao.findCartByCustomerId(customerId);
	}

	@Override
	public boolean updateCart(final Cart cart)
	{
		return cartDao.updateCart(cart);
	}

	@Override
	public boolean deleteCart(final Cart cart)
	{
		return cartDao.deleteCart(cart);
	}
}
