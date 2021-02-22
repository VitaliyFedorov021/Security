package ua.com.aimprosoft.shop.service;

import java.util.Optional;

import ua.com.aimprosoft.shop.entities.Address;
import ua.com.aimprosoft.shop.entities.Cart;
import ua.com.aimprosoft.shop.entities.Customer;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;


public interface CartService
{
	void addProductToCart(Customer customer, String productCode, int quantity);

	Cart getActiveCart(Customer customer);

	void deleteProductFromCart(Customer customer, String productCode) throws IncorrectOperationException;

	void updateProductQuantity(Customer customer, int quantity, String code) throws IncorrectOperationException;

	void placeOrder(Cart cart, Address address);

	Optional<Cart> getCartByCode(String cartCode);
}
