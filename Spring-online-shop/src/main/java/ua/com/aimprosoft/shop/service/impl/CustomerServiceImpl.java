package ua.com.aimprosoft.shop.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.com.aimprosoft.shop.dao.CustomerDao;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService
{
	private final BCryptPasswordEncoder encoder;
	private final CustomerDao customerDao;

	@Autowired
	public CustomerServiceImpl(final BCryptPasswordEncoder encoder, final CustomerDao customerDao)
	{
		this.encoder = encoder;
		this.customerDao = customerDao;
	}

	@Override
	public void registerCustomer(final Customer customer)
	{
		String password = customer.getPassword();
		customer.setPassword(encoder.encode(password));
		customerDao.insertCustomer(customer);
	}

	@Override
	public Optional<Customer> getCustomerByEmail(final String email)
	{
		return customerDao.findByEmail(email);
	}
}
