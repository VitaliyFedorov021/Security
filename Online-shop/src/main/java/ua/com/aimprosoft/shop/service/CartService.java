package ua.com.aimprosoft.shop.service;

import java.util.List;

import ua.com.aimprosoft.shop.models.Cart;


public interface CartService
{
	boolean saveCart(Cart cart);

	List<Cart> getCartByCustomerId(int customerId);

	boolean updateCart(Cart cart);

	boolean deleteCart(Cart cart);
}
