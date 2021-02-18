package ua.com.aimprosoft.shop.dao;

import java.util.Optional;

import ua.com.aimprosoft.shop.models.Cart;


public interface CartDao
{
	void insertCart(Cart cart);

	Optional<Cart> findActiveCart(String customerEmail);

	void updateCart(Cart cart);

	Optional<Cart> findCartByCode(String cartCode);
}
