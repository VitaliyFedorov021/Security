package ua.com.aimprosoft.shop.service;

import ua.com.aimprosoft.shop.dto.CustomerDto;


public interface SecurityService
{
	CustomerDto getCurrentCustomer();

	void autoLogin(String email, String password);
}
