package ua.com.aimprosoft.shop.service.impl;

import static ua.com.aimprosoft.shop.util.Cryptor.cryptPassword;

import java.security.NoSuchAlgorithmException;

import ua.com.aimprosoft.shop.dao.CustomerDao;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CustomerService;


public class CustomerServiceImpl implements CustomerService
{

	private final CustomerDao customerDao;

	public CustomerServiceImpl(final CustomerDao customerDao)
	{
		this.customerDao = customerDao;
	}

	@Override
	public Customer getCustomerByEmail(final String email)
	{
		return customerDao.findCustomerByEmail(email);
	}

	@Override
	public boolean addCustomer(final Customer customer)
	{
		try
		{
			customer.setPassword(cryptPassword(customer.getPassword()));
		}
		catch (final NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return customerDao.insertCustomer(customer);
	}

}
