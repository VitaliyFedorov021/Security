package ua.com.aimprosoft.shop.service;

import ua.com.aimprosoft.shop.models.Customer;


public interface CustomerService
{
	Customer getCustomerByEmail(String email);

	boolean addCustomer(Customer customer);
}

