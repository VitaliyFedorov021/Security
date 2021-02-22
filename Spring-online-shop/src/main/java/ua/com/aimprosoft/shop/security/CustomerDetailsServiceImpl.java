package ua.com.aimprosoft.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.com.aimprosoft.shop.entities.Customer;
import ua.com.aimprosoft.shop.service.CustomerService;


@Service
public class CustomerDetailsServiceImpl implements UserDetailsService
{
	private final CustomerService customerService;

	@Autowired
	public CustomerDetailsServiceImpl(final CustomerService customerService)
	{
		this.customerService = customerService;
	}

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException
	{
		final Customer customer = customerService.getCustomerByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Customer doesn't exist"));
		return new SecurityCustomer(customer);
	}
}
