package ua.com.shop.aimprosoft.database.service.impl;

import static ua.com.shop.aimprosoft.util.Cryptor.cryptPassword;

import java.security.NoSuchAlgorithmException;

import ua.com.shop.aimprosoft.database.dao.CustomerDao;
import ua.com.shop.aimprosoft.database.service.CustomerService;
import ua.com.shop.aimprosoft.models.Customer;


public class CustomerServiceImpl implements CustomerService
{

	private CustomerDao customerDao;

	public CustomerServiceImpl(final CustomerDao customerDao)
	{
		this.customerDao = customerDao;
	}

	@Override
	public Customer selectByLogin(final String login)
	{
		return customerDao.selectByLogin(login);
	}

	@Override
	public boolean insert(final Customer customer)
	{
		try
		{
			customer.setPassword(cryptPassword(customer.getPassword()));
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return customerDao.insert(customer);
	}

}
