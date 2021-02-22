package ua.com.aimprosoft.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ua.com.aimprosoft.shop.entities.Customer;
import ua.com.aimprosoft.shop.security.SecurityCustomer;
import ua.com.aimprosoft.shop.service.SecurityService;


@Service
public class SecurityServiceImpl implements SecurityService
{
	@Autowired
	private final UserDetailsService userDetailsService;

	@Autowired
	public SecurityServiceImpl(final UserDetailsService userDetailsService)
	{
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Customer getCurrentCustomer()
	{
		final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final SecurityCustomer securityCustomer = (SecurityCustomer) principal;
		return securityCustomer.getCustomer();
	}

	@Override
	public void autoLogin(final String email, final String password)
	{
		final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		final Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, password,
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
