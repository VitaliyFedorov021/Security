package ua.com.aimprosoft.shop.service;

import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;
import ua.com.aimprosoft.shop.models.Address;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.Customer;


public interface CartService
{
	void addProductToCart(Customer customer, int quantity, String code);

	Cart getActiveCart(Customer customer);

	void deleteProductFromCart(Customer customer, String code) throws IncorrectOperationException;

	void updateProductQuantity(Customer customer, int quantity, String code) throws IncorrectOperationException;

	void placeOrder(Cart cart, Address address);

	Cart getCartByCode(String cartCode);

}
