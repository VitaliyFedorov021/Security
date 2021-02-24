package ua.com.aimprosoft.shop.service;

import java.util.Optional;

import ua.com.aimprosoft.shop.dto.CustomerDto;
import ua.com.aimprosoft.shop.entities.Customer;


public interface CustomerService
{
	void registerCustomer(CustomerDto customerDto);

	Optional<Customer> getCustomerByEmail(String email);
}
