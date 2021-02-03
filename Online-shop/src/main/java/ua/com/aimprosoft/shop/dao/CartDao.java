package ua.com.aimprosoft.shop.dao;

import java.util.List;
import java.util.Optional;

import ua.com.aimprosoft.shop.models.Cart;


public interface CartDao
{
	boolean insertCart(Cart cart);

	List<Cart> findCartsByCustomerEmail(String customerEmail);

	Optional<Cart> findActiveCart(String customerEmail);

	boolean updateCart(Cart cart);
}
