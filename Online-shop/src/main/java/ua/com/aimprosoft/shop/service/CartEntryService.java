package ua.com.aimprosoft.shop.service;

import ua.com.aimprosoft.shop.models.Customer;


public interface CartEntryService
{
	void addEntryToCart(Customer customer, int quantity, String code);
}
