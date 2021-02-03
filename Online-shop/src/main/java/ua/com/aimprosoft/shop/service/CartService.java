package ua.com.aimprosoft.shop.service;

import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.Customer;


public interface CartService
{
	Cart getCart(Customer customer);

	boolean updateCart(Cart cart);
}
