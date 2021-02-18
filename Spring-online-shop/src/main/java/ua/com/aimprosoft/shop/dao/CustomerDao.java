package ua.com.aimprosoft.shop.dao;

import java.util.List;
import java.util.Optional;

import ua.com.aimprosoft.shop.models.Customer;


public interface CustomerDao
{
	void insertCustomer(Customer customer);

	Optional<Customer> findByEmail(String email);
}
