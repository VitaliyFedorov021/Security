package ua.com.aimprosoft.shop.service;

import java.util.Optional;

import ua.com.aimprosoft.shop.models.Customer;


public interface CustomerService
{
	Optional<Customer> getCustomerByEmail(String email);

	boolean registerCustomer(Customer customer);
}

