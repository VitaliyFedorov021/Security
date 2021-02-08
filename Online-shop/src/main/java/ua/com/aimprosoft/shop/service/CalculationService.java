package ua.com.aimprosoft.shop.service;

import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;


public interface CalculationService
{
	void calculation(Cart cart, CartEntry cartEntry);
}
