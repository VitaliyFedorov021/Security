package ua.com.aimprosoft.shop.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.com.aimprosoft.shop.dao.CustomerDao;
import ua.com.aimprosoft.shop.models.Customer;


@Service
public class CustomerDetailsServiceImpl implements UserDetailsService
{
	private final CustomerDao customerDao;

	@Autowired
	public CustomerDetailsServiceImpl(final CustomerDao customerDao)
	{
		this.customerDao = customerDao;
	}

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException
	{
		Customer customer = customerDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Customer doesn't exist"));
		return SecurityCustomer.fromCustomer(customer);
	}
}
