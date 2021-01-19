package ua.com.shop.aimprosoft.database.dao;


import ua.com.shop.aimprosoft.models.Customer;


public interface CustomerDao
{
	Customer selectByLogin(String login);

	boolean insert(Customer customer);
}
