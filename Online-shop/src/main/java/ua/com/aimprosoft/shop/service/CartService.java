package ua.com.aimprosoft.shop.service;

import java.util.Optional;

import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.Customer;


public interface CartService
{
	Optional<Cart> getCart(Customer customer);

	Cart saveCart(Customer customer);

	boolean updateCart(Cart cart);
}
