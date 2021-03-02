package ua.com.aimprosoft.shop.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import ua.com.aimprosoft.shop.dto.AddressDto;
import ua.com.aimprosoft.shop.dto.CartDto;
import ua.com.aimprosoft.shop.dto.CustomerDto;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;


public interface CartService
{
	void addProductToCart(CustomerDto customerDto, String productCode, int quantity);

	void addProductToAnonymousCart(HttpSession session, String productCode, int quantity);

	CartDto getActiveCart(CustomerDto customerDto);

	CartDto getActiveCart(HttpSession session);

	void deleteProductFromCart(CustomerDto customerDto, String productCode) throws IncorrectOperationException;

	void deleteProductFromAnonymousCart(HttpSession session, String productCode) throws IncorrectOperationException;

	void updateProductQuantity(CustomerDto customerDto, int quantity, String code) throws IncorrectOperationException;

	void updateProductQuantityAnonymous(HttpSession session, int quantity, String code) throws IncorrectOperationException;

	void placeOrder(CartDto cartDto, AddressDto addressDto);

	Optional<CartDto> getCartByCode(String cartCode);
}
