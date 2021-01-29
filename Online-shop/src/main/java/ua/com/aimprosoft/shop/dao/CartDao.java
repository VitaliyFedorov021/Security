package ua.com.aimprosoft.shop.dao;

import java.util.List;

import ua.com.aimprosoft.shop.models.Cart;


public interface CartDao
{
	boolean insertCart(Cart cart);

	List<Cart> findCartByCustomerId(int customerId);

	boolean updateCart(Cart cart);

	boolean deleteCart(Cart cart);
}
