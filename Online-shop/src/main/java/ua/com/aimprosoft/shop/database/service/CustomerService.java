package ua.com.shop.aimprosoft.database.service;

import ua.com.shop.aimprosoft.database.dao.CustomerDao;
import ua.com.shop.aimprosoft.models.Customer;


public interface CustomerService
{
	Customer selectByLogin(String login);

	boolean insert(Customer customer);
}

