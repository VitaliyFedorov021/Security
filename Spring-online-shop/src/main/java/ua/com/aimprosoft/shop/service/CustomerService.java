package ua.com.aimprosoft.shop.service;

import java.util.Optional;

import ua.com.aimprosoft.shop.models.Customer;


public interface CustomerService
{
	void registerCustomer(Customer customer);

	Optional<Customer> getCustomerByEmail(String email);
}
