package ua.com.aimprosoft.shop.service;

import java.util.Optional;

import ua.com.aimprosoft.shop.dto.AddressDto;
import ua.com.aimprosoft.shop.dto.CartDto;
import ua.com.aimprosoft.shop.dto.CustomerDto;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;


public interface CartService
{
	void addProductToCart(CustomerDto customerDto, String productCode, int quantity);

	CartDto getActiveCart(CustomerDto customerDto);

	void deleteProductFromCart(CustomerDto customerDto, String productCode) throws IncorrectOperationException;

	void updateProductQuantity(CustomerDto customerDto, int quantity, String code) throws IncorrectOperationException;

	void placeOrder(CartDto cartDto, AddressDto addressDto);

	Optional<CartDto> getCartByCode(String cartCode);
}
