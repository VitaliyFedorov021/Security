package ua.com.aimprosoft.shop.dao;


import java.util.Optional;

import ua.com.aimprosoft.shop.models.Customer;


public interface CustomerDao
{
	Optional<Customer> findCustomerByEmail(String email);

	boolean insertCustomer(Customer customer);
}
