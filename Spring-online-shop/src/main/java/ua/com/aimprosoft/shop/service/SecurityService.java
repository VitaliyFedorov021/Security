package ua.com.aimprosoft.shop.service;

import ua.com.aimprosoft.shop.entities.Customer;


public interface SecurityService
{
	Customer getCurrentCustomer();

	void autoLogin(String email, String password);
}
