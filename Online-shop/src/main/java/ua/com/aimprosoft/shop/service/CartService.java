package ua.com.aimprosoft.shop.service;

import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.Customer;


public interface CartService
{
	void addProductToCart(Customer customer, int quantity, String code);
	
	Cart getActiveCart(Customer customer);

	Cart getCartByEntryId(int entryId);
}
