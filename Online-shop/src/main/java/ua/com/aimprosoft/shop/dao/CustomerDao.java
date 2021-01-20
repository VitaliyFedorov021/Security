package ua.com.aimprosoft.shop.dao;


import ua.com.aimprosoft.shop.models.Customer;


public interface CustomerDao
{
	Customer findCustomerByEmail(String email);

	boolean insertCustomer(Customer customer);
}
