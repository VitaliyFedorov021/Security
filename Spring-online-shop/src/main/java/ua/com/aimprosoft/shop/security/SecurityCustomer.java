package ua.com.aimprosoft.shop.security;

import java.util.HashSet;

import org.springframework.security.core.userdetails.User;

import ua.com.aimprosoft.shop.entities.Customer;


public class SecurityCustomer extends User
{
	private Customer customer;

	public SecurityCustomer(final Customer customer)
	{
		super(customer.getEmail(), customer.getPassword(), new HashSet<>());
		setCustomer(customer);
	}

	public void setCustomer(final Customer customer)
	{
		this.customer = customer;
	}

	public Customer getCustomer()
	{
		return customer;
	}
}
